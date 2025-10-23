package com.iucosoft.mylinksspringboot.service.impl.bridge;

import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.bridge.LinkOwner;
import com.iucosoft.mylinksspringboot.repositories.bridge.LinkOwnerRepository;
import com.iucosoft.mylinksspringboot.service.bridge.LinkOwnerService;
import com.iucosoft.mylinksspringboot.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class LinkOwnerServiceImpl extends AbstractServiceImpl<LinkOwner, Long> implements LinkOwnerService {
    private final LinkOwnerRepository linkOwnerRepository;

    @Autowired
    public LinkOwnerServiceImpl(LinkOwnerRepository linkOwnerRepository) {
        this.linkOwnerRepository = linkOwnerRepository;
    }

    @Override
    protected JpaRepository<LinkOwner, Long> getRepository() {
        return linkOwnerRepository;
    }

    @Override
    public Page<Link> getLinksByOwnerId(Long ownerId, Pageable pageable) {
        return linkOwnerRepository.findLinksByOwnerId(ownerId, pageable);
    }

    @Override
    public void deleteLinkOwnerRelationshipByLinkId(Long linkId) {
        linkOwnerRepository.deleteLinkOwnerByLinkId(linkId);
    }
}
