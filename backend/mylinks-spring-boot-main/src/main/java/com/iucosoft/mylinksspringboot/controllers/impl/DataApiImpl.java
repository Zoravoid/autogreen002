package com.iucosoft.mylinksspringboot.controllers.impl;

import com.iucosoft.mylinksspringboot.controllers.AbstractExceptionHandler;
import com.iucosoft.mylinksspringboot.controllers.DataApi;
import com.iucosoft.mylinksspringboot.entities.Tag;
import com.iucosoft.mylinksspringboot.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DataApiImpl extends AbstractExceptionHandler implements DataApi {

    @Autowired
    private TagService tagService;

    @Override
    public ResponseEntity<List<String>> getAllTags() {
        List<String> tagTitles = new ArrayList<>();
        List<Tag>  tags = tagService.getAllTags();
        for (Tag tag : tags) {
            tagTitles.add(tag.getTitle());
        }
        return ResponseEntity.ok(tagTitles);
    }
}
