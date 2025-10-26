package com.iucosoft.mylinksspringboot.service.impl;

import com.iucosoft.mylinksspringboot.entities.Tag;
import com.iucosoft.mylinksspringboot.entities.bridge.LinkTag;
import com.iucosoft.mylinksspringboot.repositories.TagRepository;
import com.iucosoft.mylinksspringboot.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl extends AbstractServiceImpl<Tag, Long> implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag createTag(Tag tagToSave) {
        return tagRepository.save(tagToSave);
    }

    @Override
    public Tag updateTag(Tag tagToUpdate) {
        return tagRepository.save(tagToUpdate);
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));
    }

    @Override
    public List<Tag> getAllNewAndExisting(List<String> tagTitles) {
        List<Tag> existingTags = tagRepository.findByTitleInExactCase(tagTitles);

        Set<String> existingTitles = existingTags.stream()
                .map(Tag::getTitle)
                .collect(Collectors.toSet());

        Set<String> newTitles = tagTitles.stream()
                .filter(title -> !existingTitles.contains(title))
                .collect(Collectors.toSet());

        List<Tag> newTags = newTitles.stream()
                .map(title -> new Tag(null, title))
                .collect(Collectors.toList());

        tagRepository.saveAll(newTags);
        existingTags.addAll(newTags);
        return existingTags;
    }

    @Override
    protected JpaRepository<Tag, Long> getRepository() {
        return tagRepository;
    }
}
