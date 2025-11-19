package com.iucosoft.mylinksspringboot.mappers.custom.impl;

import com.iucosoft.mylinksspringboot.dto.category.CategoryDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkCreateDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkUpdateDTO;
import com.iucosoft.mylinksspringboot.entities.Category;
import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.Tag;
import com.iucosoft.mylinksspringboot.entities.bridge.LinkCategory;
import com.iucosoft.mylinksspringboot.entities.bridge.LinkTag;
import com.iucosoft.mylinksspringboot.mappers.LinkMapper;
import com.iucosoft.mylinksspringboot.mappers.custom.CustomCategoryMapper;
import com.iucosoft.mylinksspringboot.mappers.custom.CustomLinkMapper;
import com.iucosoft.mylinksspringboot.mappers.custom.CustomUserMapper;
import com.iucosoft.mylinksspringboot.service.TagService;
import com.iucosoft.mylinksspringboot.service.UserService;
import com.iucosoft.mylinksspringboot.service.bridge.LinkCategoryService;
import com.iucosoft.mylinksspringboot.service.bridge.LinkTagService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomLinkMapperImpl implements CustomLinkMapper {

    LinkMapper linkMapper = Mappers.getMapper(LinkMapper.class);

    private final CustomCategoryMapper customCategoryMapper;
    private final LinkCategoryService linkCategoryService;
    private final CustomUserMapper customUserMapper;
    private final LinkTagService linkTagService;
    private final UserService userService;

    public CustomLinkMapperImpl(CustomCategoryMapper customCategoryMapper, LinkCategoryService linkCategoryService, CustomUserMapper customUserMapper, LinkTagService linkTagService, UserService userService) {
        this.customCategoryMapper = customCategoryMapper;
        this.linkCategoryService = linkCategoryService;
        this.customUserMapper = customUserMapper;
        this.linkTagService = linkTagService;
        this.userService = userService;
    }

    @Override
    public LinkDTO toDto(Link link) {
        LinkDTO linkDTO = linkMapper.toDto(link);
        linkDTO.setOwner(customUserMapper.toDto(link.getOwner()));
        List<Tag> tags = linkTagService.getTagsByLink(link);
        Set<String> tagTitles = tags.stream().map(Tag::getTitle).collect(Collectors.toSet());
        linkDTO.setTagTitles(tagTitles);
        List<LinkCategory> categoryLinks = linkCategoryService.getLinkCategoryListByLinkId(link.getId());
        List<Category> categories = new ArrayList<>();
        for (LinkCategory linkCategory : categoryLinks) {
            categories.add(linkCategory.getCategory());
        }
        List<CategoryDTO> categoriesDTO = customCategoryMapper.toDtos(categories);
        linkDTO.setCategories(categoriesDTO);
        return linkDTO;
    }

    @Override
    public Link toEntity(LinkDTO dto) {
        return linkMapper.toEntity(dto);
    }

    @Override
    public Link toEntity(LinkUpdateDTO dto) {
        return linkMapper.toEntity(dto);
    }

    @Override
    public LinkUpdateDTO toUpdateDto(Link link) {
        LinkUpdateDTO linkUpdateDTO = linkMapper.toUpdateDto(link);
        List<Tag> tags = linkTagService.getTagsByLink(link);
        Set<String> tagTitles = tags.stream().map(Tag::getTitle).collect(Collectors.toSet());
        linkUpdateDTO.setTagTitles(tagTitles);
        return linkUpdateDTO;
    }

    @Override
    public Link toEntity(LinkCreateDTO linkCreateDTO) {
        Link link = linkMapper.toEntity(linkCreateDTO);
        link.setDateLastAccessed(new Date());
        link.setOwner(userService.findById(linkCreateDTO.getOwnerId()));
        return link;
    }

    @Override
    public LinkCreateDTO toCreateDto(Link link) {
        return linkMapper.toCreateDto(link);
    }
}
