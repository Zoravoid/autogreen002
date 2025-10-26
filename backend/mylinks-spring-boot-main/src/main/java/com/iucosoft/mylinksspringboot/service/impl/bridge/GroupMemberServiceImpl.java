package com.iucosoft.mylinksspringboot.service.impl.bridge;

import com.iucosoft.mylinksspringboot.dto.group.GroupDTO;
import com.iucosoft.mylinksspringboot.entities.Group;
import com.iucosoft.mylinksspringboot.entities.bridge.GroupMember;
import com.iucosoft.mylinksspringboot.mappers.GroupMapper;
import com.iucosoft.mylinksspringboot.repositories.bridge.GroupMemberRepository;
import com.iucosoft.mylinksspringboot.service.bridge.GroupMemberService;
import com.iucosoft.mylinksspringboot.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupMemberServiceImpl extends AbstractServiceImpl<GroupMember, Long> implements GroupMemberService {
    private final GroupMemberRepository groupMemberRepository;
    private final GroupMapper groupMapper;

    @Autowired
    public GroupMemberServiceImpl(GroupMemberRepository groupMemberRepository, GroupMapper groupMapper) {
        this.groupMemberRepository = groupMemberRepository;
        this.groupMapper = groupMapper;
    }


    @Override
    protected JpaRepository<GroupMember, Long> getRepository() {
        return groupMemberRepository;
    }

    @Override
    public Page<Group> getGroupsByUserMemberId(Long userId, Pageable pageable) {
        Page<Group> userGroups = groupMemberRepository.findByUserMember(userId, pageable);
        return userGroups;
    }

    @Override
    public void deleteByMemberId(Long memberId) {
        groupMemberRepository.deleteByMemberId(memberId);
    }

    @Override
    public void deleteByGroupId(Long groupId) {
        groupMemberRepository.deleteByGroupId(groupId);
    }

    @Override
    public boolean existsByMemberIdAndAndGroupId(Long memberId, Long groupId) {
        return groupMemberRepository.existsByMemberIdAndAndGroupId(memberId, groupId);
    }

    @Override
    public void deleteByGroupIdAndMemberId(Long groupId, Long memberId) {
        groupMemberRepository.deleteByGroupIdAndMemberId(groupId,memberId);
    }


}
