package com.iucosoft.mylinksspringboot.service.bridge;

import com.iucosoft.mylinksspringboot.dto.group.GroupDTO;
import com.iucosoft.mylinksspringboot.entities.Group;
import com.iucosoft.mylinksspringboot.entities.bridge.GroupMember;
import com.iucosoft.mylinksspringboot.service.OperationIntf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupMemberService extends OperationIntf<GroupMember, Long> {
    Page<Group> getGroupsByUserMemberId(Long userId, Pageable pageable);

    void deleteByMemberId(Long memberId);

    void deleteByGroupId(Long groupId);

    boolean existsByMemberIdAndAndGroupId(final Long memberId, final Long groupId);

    void deleteByGroupIdAndMemberId(final Long groupId, final Long memberId);
}
