package com.iucosoft.mylinksspringboot.service.bridge;

import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.bridge.GroupLink;
import com.iucosoft.mylinksspringboot.service.OperationIntf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupLinkService extends OperationIntf<GroupLink, Long> {

    Page<Link> getLinksByGroupId(Long groupId, Pageable pageable);

    Link getLinkInGroup(Long groupId, Long linkId);

    void deleteByLinkId(Long linkId);

    void deleteByGroupId(Long groupId);

    void deleteLinkFromGroupByGroupIdAndLinkId(Long groupId, Long linkId);
}
