package com.iucosoft.mylinksspringboot.repositories.bridge;

import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.bridge.LinkOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkOwnerRepository extends JpaRepository<LinkOwner, Long> {

    @Query("SELECT lo.link FROM LinkOwner lo WHERE lo.owner.id = :ownerId")
    Page<Link> findLinksByOwnerId(final @Param("ownerId") Long ownerId, Pageable pageable);

    @Modifying
    @Query("DELETE FROM LinkOwner lo WHERE lo.link.id =:linkId")
    void deleteLinkOwnerByLinkId(final @Param("linkId") Long linkId);
}
