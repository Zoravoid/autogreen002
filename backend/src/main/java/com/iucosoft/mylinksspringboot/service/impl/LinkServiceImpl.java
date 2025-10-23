package com.iucosoft.mylinksspringboot.service.impl;

import com.iucosoft.mylinksspringboot.dto.MessageDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkDTO;
import com.iucosoft.mylinksspringboot.entities.*;
import com.iucosoft.mylinksspringboot.entities.bridge.LinkCategory;
import com.iucosoft.mylinksspringboot.exceptions.ResourceNotFoundException;
import com.iucosoft.mylinksspringboot.mappers.LinkMapper;
import com.iucosoft.mylinksspringboot.repositories.LinkRepository;
import com.iucosoft.mylinksspringboot.service.CategoryService;
import com.iucosoft.mylinksspringboot.service.LinkService;
import com.iucosoft.mylinksspringboot.service.TagService;
import com.iucosoft.mylinksspringboot.service.bridge.GroupLinkService;
import com.iucosoft.mylinksspringboot.service.bridge.LinkCategoryService;
import com.iucosoft.mylinksspringboot.service.bridge.LinkOwnerService;
import com.iucosoft.mylinksspringboot.service.bridge.LinkTagService;
import com.iucosoft.mylinksspringboot.util.AppConstants;
import com.iucosoft.mylinksspringboot.util.SortingUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

//@Transactional
@Service
public class LinkServiceImpl extends AbstractServiceImpl<Link, Long> implements LinkService {
    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private LinkTagService linkTagService;
    @Autowired
    private TagService tagService;
    @Autowired
    private LinkOwnerService linkOwnerService;
    @Autowired
    private GroupLinkService groupLinkService;
    @Autowired
    private LinkCategoryService linkCategoryService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LinkMapper linkMapper;

    @Override
    public Link save(Link entity) {
        return super.save(entity);
    }

    @Override
    public Link findById(Long id) {
        return linkRepository.findAllById(Collections.singleton(id)).stream()
                             .findFirst().orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Link createLink(Link link, Set<String> tagTitles, List<Long> categoryIds) {
        final Link savedLink = linkRepository.save(link);
        final List<LinkCategory> linkCategoryList = getLinkCategoryList(categoryIds, savedLink);
        linkCategoryService.save(linkCategoryList);
        List<Tag> tags = tagService.getAllNewAndExisting(new ArrayList<>(tagTitles));
        linkTagService.saveLinkTagConnection(link, tags);
        return savedLink;
    }

    @Override
    public Link update(long id, Link newLink, Set<String> tagTitles, List<Long> categoryIds) {
        Link link = findById(id);
        linkCategoryService.deleteByLinkId(id);
        BeanUtils.copyProperties(newLink, link, "id", "owner");
        final Link updatedLink = linkRepository.save(link);
        final List<LinkCategory> linkCategoryList = getLinkCategoryList(categoryIds, updatedLink);
        linkCategoryService.save(linkCategoryList);
        List<Tag> updatedTags = tagService.getAllNewAndExisting(new ArrayList<>(tagTitles));
        linkTagService.saveLinkTagConnection(link, updatedTags);
        return updatedLink;
    }

    @Override
    public Page<Link> getAllLinksByOwnerId(Long ownerId, Pageable pageable) {
        return linkOwnerService.getLinksByOwnerId(ownerId, pageable);
    }

    @Override
    public User getOwnerByLinkId(Long linkId) {
        return linkRepository.getOwnerByLinkId(linkId);
    }

    @Override
    public Group getGroupByLinkId(Long linkId) {
        return linkRepository.getGroupByLinkId(linkId);
    }

    @Override
    public Page<Category> getCategoriesByLinkId(Long linkId, Pageable pageable) {
        return linkRepository.getCategoriesByLinkId(linkId, pageable);
    }

    @Override
    public MessageDTO delete(Long linkId) {
        linkTagService.deleteAllByLinkId(linkId);
        linkCategoryService.deleteByLinkId(linkId);
        linkRepository.deleteById(linkId);

        final String message = String.format("Link with id = %s deleted successfully", linkId);
        return new MessageDTO(message, true);
    }

    @Override
    public Page<Link> getFilteredLinks(String title,
                                       String url,
                                       Visibility visibility,
                                       List<Long> categoryIds,
                                       List<String> tagTitles,
                                       Pageable pageable) {
        categoryIds = ((categoryIds != null) && categoryIds.isEmpty()) ? null : categoryIds;
        tagTitles = (tagTitles != null) && tagTitles.isEmpty() ? null : tagTitles;
        pageable = SortingUtil.applyDefaultSorting(pageable, Link.class, AppConstants.Fields.TITLE);

        Page<Link> links = linkRepository.getLinksByFilterParams(title, url, visibility, categoryIds, tagTitles, pageable);
        return links;
    }

    @Override
    protected JpaRepository<Link, Long> getRepository() {
        return linkRepository;
    }

    private List<LinkCategory> getLinkCategoryList(final List<Long> categoryIds, final Link link) {
        return categoryIds.stream().map(cId ->
                LinkCategory.builder().category(categoryService.findById(cId)).link(link).build()
        ).collect(Collectors.toList());
    }

    private void deleteAllRelationshipsWithLink(Link link) {
        linkCategoryService.deleteByLinkId(link.getId());
        groupLinkService.deleteByLinkId(link.getId());
        linkOwnerService.deleteLinkOwnerRelationshipByLinkId(link.getId());
    }
}
