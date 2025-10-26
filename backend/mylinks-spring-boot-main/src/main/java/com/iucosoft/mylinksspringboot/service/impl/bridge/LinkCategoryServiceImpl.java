package com.iucosoft.mylinksspringboot.service.impl.bridge;

import com.iucosoft.mylinksspringboot.dto.link.LinkDTO;
import com.iucosoft.mylinksspringboot.entities.Category;
import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.bridge.LinkCategory;
import com.iucosoft.mylinksspringboot.mappers.LinkMapper;
import com.iucosoft.mylinksspringboot.repositories.bridge.LinkCategoryRepository;
import com.iucosoft.mylinksspringboot.service.CategoryService;
import com.iucosoft.mylinksspringboot.service.LinkService;
import com.iucosoft.mylinksspringboot.service.bridge.LinkCategoryService;
import com.iucosoft.mylinksspringboot.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinkCategoryServiceImpl extends AbstractServiceImpl<LinkCategory, Long> implements LinkCategoryService {

    private final LinkCategoryRepository linkCategoryRepository;
    private final LinkMapper linkMapper;


    @Autowired
    public LinkCategoryServiceImpl(LinkCategoryRepository linkCategoryRepository, LinkMapper linkMapper) {
        this.linkCategoryRepository = linkCategoryRepository;
        this.linkMapper = linkMapper;
    }

    @Override
    public void deleteByCategoryId(Long categoryId) {
        linkCategoryRepository.deleteByCategoryId(categoryId);
    }

    @Override
    public void deleteByLinkId(Long linkId) {
        linkCategoryRepository.deleteByLinkId(linkId);
    }

    @Override
    public Page<LinkDTO> getLinksByCategoryId(Long categoryId, Pageable pageable) {
        Page<Link> links = linkCategoryRepository.findLinksByCategoryId(categoryId, pageable);

        return links.map(linkMapper::toDto);
    }

    @Override
    public List<LinkCategory> getLinkCategoryListByLinkId(Long linkId) {
        return linkCategoryRepository.findLinkCategoriesByLinkId(linkId);
    }

    @Override
    public List<LinkCategory> getLinkCategoryListByCategoryId(Long categoryId) {
        return null;
    }

    @Override
    public boolean existsByCategoryIdAndLinkId(Long categoryId, Long linkId) {
        return linkCategoryRepository.existsByCategoryIdAndLinkId(categoryId, linkId);
    }

    @Override
    public void removeLinkFomCategory(Long categoryId, Link link) {
        linkCategoryRepository.deleteByCategoryIdAndLinkId(categoryId, link.getId());
    }

//    @Override
//    public void editLinksInCategory(Long categoryId, List<Long> linkIds) {
//        deleteByCategoryId(categoryId);
//        final Category category = categoryService.findById(categoryId);
//
//        final List<LinkCategory> linkCategoriesToSave = linkIds.stream().map(id -> LinkCategory.builder().link(linkService.findById(id)).category(category).build()).collect(Collectors.toList());
//
//        save(linkCategoriesToSave);
//    }

    @Override
    protected JpaRepository<LinkCategory, Long> getRepository() {
        return linkCategoryRepository;
    }
}
