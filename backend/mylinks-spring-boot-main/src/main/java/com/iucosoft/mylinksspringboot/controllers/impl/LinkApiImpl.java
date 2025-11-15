package com.iucosoft.mylinksspringboot.controllers.impl;

import com.iucosoft.mylinksspringboot.context.UserContext;
import com.iucosoft.mylinksspringboot.controllers.AbstractExceptionHandler;
import com.iucosoft.mylinksspringboot.controllers.LinkApi;
import com.iucosoft.mylinksspringboot.dto.MessageDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkCreateDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkUpdateDTO;
import com.iucosoft.mylinksspringboot.entities.Category;
import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.Visibility;
import com.iucosoft.mylinksspringboot.entities.bridge.LinkCategory;
import com.iucosoft.mylinksspringboot.mappers.GroupMapper;
import com.iucosoft.mylinksspringboot.mappers.custom.CustomCategoryMapper;
import com.iucosoft.mylinksspringboot.mappers.custom.CustomLinkMapper;
import com.iucosoft.mylinksspringboot.mappers.custom.CustomUserMapper;
import com.iucosoft.mylinksspringboot.service.CategoryService;
import com.iucosoft.mylinksspringboot.service.GroupService;
import com.iucosoft.mylinksspringboot.service.UserService;
import com.iucosoft.mylinksspringboot.service.bridge.LinkCategoryService;
import com.iucosoft.mylinksspringboot.service.impl.LinkServiceImpl;
import com.iucosoft.mylinksspringboot.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class LinkApiImpl extends AbstractExceptionHandler implements LinkApi {
    @Autowired
    private LinkServiceImpl linkService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LinkCategoryService linkCategoryService;
    @Autowired
    private CustomLinkMapper customLinkMapper;
    @Autowired
    private GroupService groupService;
    @Autowired
    private CustomCategoryMapper customCategoryMapper;

    @Override
    public ResponseEntity<Page<LinkDTO>> getLinks(Pageable pageable) {
        Page<Link> links = linkService.findAllPaginated(pageable);
        Page<LinkDTO> linkDTOPage = links.map(link -> customLinkMapper.toDto(link));
        return ResponseEntity.ok(linkDTOPage);
    }

    @Override
    public ResponseEntity<Page<LinkDTO>> getLinksFiltered(String title, String url, Visibility visibility, List<Long> categoryIds, List<String> tagTitles, Pageable pageable) {
        Page<Link> links = linkService.getFilteredLinks(title, url, visibility, categoryIds, tagTitles, pageable);
        Page<LinkDTO> linkDTOPage = links.map(link -> customLinkMapper.toDto(link));
        return ResponseEntity.ok(linkDTOPage);
    }

    @Override
    public ResponseEntity<LinkDTO> createLink(LinkCreateDTO linkCreateDTO) {
        final Long userId = UserContext.getUserId();
        linkCreateDTO.setOwnerId(userId);
        final Link linkToSave = customLinkMapper.toEntity(linkCreateDTO);
        final Set<String> tagTitles = linkCreateDTO.getTagTitles();
        final List<Long> categoryIds = linkCreateDTO.getCategoryIds();
        final Link createdLink = linkService.createLink(linkToSave, tagTitles, categoryIds);
        final LinkDTO linkDTOCreated = customLinkMapper.toDto(createdLink);
        return ResponseEntity.ok(linkDTOCreated);
    }

    @Override
    public ResponseEntity<LinkUpdateDTO> updateLinkById(Long linkId, LinkUpdateDTO linkUpdateDTO) {
        final Link link = customLinkMapper.toEntity(linkUpdateDTO);
        final List<Long> categoryIds = linkUpdateDTO.getCategoryIds();
        final Set<String> tagTitles = linkUpdateDTO.getTagTitles();
        final Link updatedLink = linkService.update(linkId, link, tagTitles, categoryIds);
        final LinkUpdateDTO updatedLinkUpdateDTO = customLinkMapper.toUpdateDto(updatedLink);
        return ResponseEntity.ok(updatedLinkUpdateDTO);
    }

    @Override
    public ResponseEntity<LinkDTO> getLinkById(Long linkId) {
        return ResponseEntity.ok(customLinkMapper.toDto(linkService.findById(linkId)));
    }

    @Override
    public ResponseEntity<MessageDTO> deleteLinkById(Long linkId) {
        final MessageDTO messageDTO = linkService.delete(linkId);
        return ResponseEntity.ok().body(messageDTO);
    }

    @Override
    public ResponseEntity<CategoryDTO> addLinkToCategory(Long categoryId, LinkDTO linkDTO) throws Exception {
        final Link link = linkService.findById(linkDTO.getId());
        final Category category = categoryService.findById(categoryId);
        if (!linkCategoryService.existsByCategoryIdAndLinkId(categoryId, linkDTO.getId())) {
            final LinkCategory linkCategoryToSave = LinkCategory.builder().category(category).link(link).build();
            final LinkCategory savedLinkCategory = linkCategoryService.save(linkCategoryToSave);
            final Category updatedCategory = categoryService.findById(savedLinkCategory.getId());
            final CategoryDTO updatedCategoryDTO = customCategoryMapper.toDto(updatedCategory);
            return ResponseEntity.ok().body(updatedCategoryDTO);
        } else {
            throw new Exception("Link is already in category.");
        }
    }

//    @Override
//    public ResponseEntity<CategoryDTO> editLinksInCategory(Long categoryId, List<Long> linkIds) {
//        linkCategoryService.editLinksInCategory(categoryId, linkIds);
//        final Category updatedCategory = categoryService.findById(categoryId);
//        return ResponseEntity.ok().body(customCategoryMapper.toDto(updatedCategory));
//    }

    @Override
    public ResponseEntity<MessageDTO> removeLinkFromCategory(Long categoryId, LinkDTO linkDTO) {
        linkCategoryService.removeLinkFomCategory(categoryId, customLinkMapper.toEntity(linkDTO));
        final MessageDTO messageDTO = new MessageDTO(String.format(AppConstants.Messages.LINK_REMOVED_FROM_CATEGORY_TEMPLATE, linkDTO.getId(), categoryId), true);
        return ResponseEntity.ok().body(messageDTO);
    }

    @Override
    public ResponseEntity<Page<LinkDTO>> getLinksByOwnerId(Long ownerId, Pageable pageable) {
//        return ResponseEntity.ok(linkService.getAllLinksByOwnerId(ownerId, pageable)); //TODO
        return null;
    }

    @Override
    public ResponseEntity<Page<LinkDTO>> getLinksByGroupId(Long groupId, Pageable pageable) {
        return ResponseEntity.ok(groupService.getLinksByGroupId(groupId, pageable));
    }

    @Override
    public ResponseEntity<Page<LinkDTO>> getLinksByCategoryId(Long categoryId, Pageable pageable) {
        Page<LinkDTO> links = categoryService.getLinksByCategoryId(categoryId, pageable).map(link -> customLinkMapper.toDto(link));
        return ResponseEntity.ok().body(links);
    }

    @Override
    public ResponseEntity<Visibility[]> getVisibilityOptions() {
        return ResponseEntity.ok(Visibility.values());
    }
}
