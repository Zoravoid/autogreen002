package com.iucosoft.mylinksspringboot.service.impl.bridge;

import com.iucosoft.mylinksspringboot.dto.link.LinkDTO;
import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.bridge.GroupLink;
import com.iucosoft.mylinksspringboot.exceptions.ResourceNotFoundException;
import com.iucosoft.mylinksspringboot.mappers.LinkMapper;
import com.iucosoft.mylinksspringboot.repositories.bridge.GroupLinkRepository;
import com.iucosoft.mylinksspringboot.service.bridge.GroupLinkService;
import com.iucosoft.mylinksspringboot.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupLinkServiceImpl extends AbstractServiceImpl<GroupLink, Long> implements GroupLinkService {
    private final GroupLinkRepository groupLinkRepository;

    @Autowired
    public GroupLinkServiceImpl(GroupLinkRepository groupLinkRepository) {
        this.groupLinkRepository = groupLinkRepository;
    }


    @Override
    public Page<Link> getLinksByGroupId(Long groupId, Pageable pageable) {
        return groupLinkRepository.findGroupLinksByGroupId(groupId, pageable);
    }

    @Override
    public Link getLinkInGroup(Long groupId, Long linkId) {
        return groupLinkRepository.findLinkInGroupByGroupIdAndLinkId(groupId, linkId).orElseThrow(
                () -> new ResourceNotFoundException("There is no such link in this group"));
    }

    @Override
    public void deleteByLinkId(Long linkId) {
        groupLinkRepository.deleteByLinkId(linkId);
    }

    @Override
    public void deleteByGroupId(Long groupId) {
        groupLinkRepository.deleteByGroupId(groupId);
    }

    @Override
    public void deleteLinkFromGroupByGroupIdAndLinkId(Long groupId, Long linkId) {
        groupLinkRepository.findLinkInGroupByGroupIdAndLinkId(groupId, linkId).orElseThrow(
                () -> new ResourceNotFoundException("There is no such link in this group"));
        groupLinkRepository.deleteLinkFromGroupByGroupIdAndLinkId(groupId, linkId);
    }

    @Override
    protected JpaRepository<GroupLink, Long> getRepository() {
        return groupLinkRepository;
    }
}
