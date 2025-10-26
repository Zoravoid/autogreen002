package com.iucosoft.mylinksspringboot.repositories.bridge;

import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.bridge.GroupLink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupLinkRepository extends JpaRepository<GroupLink, Long> {

    @Query("SELECT gl.link FROM GroupLink gl WHERE gl.group.id = :groupId")
    Page<Link> findGroupLinksByGroupId(final @Param("groupId") Long groupId, final Pageable pageable);

    @Query("SELECT gl.link FROM GroupLink gl WHERE gl.group.id = :groupId AND gl.link.id = :linkId")
    Optional<Link> findLinkInGroupByGroupIdAndLinkId(final @Param("groupId") Long groupId, final @Param("linkId") Long linkId);

    @Modifying
    @Query("DELETE FROM GroupLink gm WHERE  gm.group.id=:groupId")
    void deleteByGroupId(final @Param("groupId") Long groupId);

    @Modifying
    @Query("DELETE FROM GroupLink gm WHERE  gm.link.id = :linkId")
    void deleteByLinkId(final @Param("linkId") Long linkId);

    @Modifying
    @Query("DELETE FROM GroupLink gm WHERE  gm.group.id=:groupId AND gm.link.id = :linkId")
    void deleteLinkFromGroupByGroupIdAndLinkId(final @Param("groupId") Long groupId, final @Param("linkId") Long linkId);
}
