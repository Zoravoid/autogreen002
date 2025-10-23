package com.iucosoft.mylinksspringboot.service.impl.bridge;

import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.Tag;
import com.iucosoft.mylinksspringboot.entities.bridge.LinkTag;
import com.iucosoft.mylinksspringboot.repositories.bridge.LinkTagRepository;
import com.iucosoft.mylinksspringboot.service.TagService;
import com.iucosoft.mylinksspringboot.service.bridge.LinkTagService;
import com.iucosoft.mylinksspringboot.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class LinkTagServiceImpl extends AbstractServiceImpl<LinkTag, Long> implements LinkTagService {
    @Autowired
    private LinkTagRepository linkTagRepository;

    @Override
    protected JpaRepository<LinkTag, Long> getRepository() {
        return linkTagRepository;
    }

    @Override
    public List<LinkTag> saveLinkTagConnection(Link link, List<Tag> updatedTags) {
        List<LinkTag> existingConnections = linkTagRepository.findByLinkId(link.getId());
        List<Long> existingTagIds = existingConnections.stream()
                .map(lt -> lt.getTag().getId())
                .collect(Collectors.toList());

        List<Long> updatedTagIds = updatedTags.stream()
                .map(Tag::getId)
                .collect(Collectors.toList());

        List<LinkTag> toAdd = updatedTags.stream()
                .filter(tag -> !existingTagIds.contains(tag.getId()))
                .map(tag -> LinkTag.builder()
                        .link(link)
                        .tag(tag)
                        .build())
                .collect(Collectors.toList());

        List<LinkTag> toRemove = existingConnections.stream()
                .filter(lt -> !updatedTagIds.contains(lt.getTag().getId()))
                .collect(Collectors.toList());

        if (!toRemove.isEmpty()) {
            linkTagRepository.deleteAll(toRemove);
        }
        if (!toAdd.isEmpty()) {
            linkTagRepository.saveAll(toAdd);
        }

        return linkTagRepository.findByLinkId(link.getId());
    }

    @Override
    public List<Tag> getTagsByLink(Link link) {
        List<LinkTag> linkTags = linkTagRepository.findByLinkId(link.getId());
        return linkTags.stream()
                .map(LinkTag::getTag)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllByLinkId(Long linkId) {
        linkTagRepository.deleteAllByLinkId(linkId);
    }
}
