package com.iucosoft.mylinksspringboot.service.bridge;

import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.bridge.LinkOwner;
import com.iucosoft.mylinksspringboot.service.OperationIntf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LinkOwnerService extends OperationIntf<LinkOwner, Long> {

    Page<Link> getLinksByOwnerId(Long ownerId, Pageable pageable);

    void deleteLinkOwnerRelationshipByLinkId(Long linkId);
}
