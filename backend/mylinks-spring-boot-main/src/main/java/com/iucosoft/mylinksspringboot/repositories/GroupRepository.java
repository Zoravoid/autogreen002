package com.iucosoft.mylinksspringboot.repositories;

import com.iucosoft.mylinksspringboot.entities.Group;
import com.iucosoft.mylinksspringboot.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("SELECT ug FROM Group ug WHERE ug.owner.id = :userId")
    Page<Group> findGroupsByUserOwnerId(final @Param("userId") Long userId, Pageable pageable);

    @Query("SELECT g.owner FROM Group g WHERE g.id = :groupId")
    User findOwnerByGroupId(final @Param("groupId") Long groupId);

    @Query("SELECT gm.member FROM GroupMember gm WHERE gm.group.id = :groupId")
    Page<User> getMembersByGroupId(final @Param("groupId") Long groupId, Pageable pageable);
}
