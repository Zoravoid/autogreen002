package com.iucosoft.mylinksspringboot.service;

import com.iucosoft.mylinksspringboot.entities.Tag;
import com.iucosoft.mylinksspringboot.entities.bridge.LinkTag;

import java.util.List;
import java.util.Set;

public interface TagService extends OperationIntf<Tag, Long> {
    Tag createTag(Tag tagToSave);

    Tag updateTag(Tag tagToUpdate);

    void deleteTag(Long id);

    List<Tag> getAllTags();

    List<Tag> getAllNewAndExisting(List<String> tagTitles);
}
