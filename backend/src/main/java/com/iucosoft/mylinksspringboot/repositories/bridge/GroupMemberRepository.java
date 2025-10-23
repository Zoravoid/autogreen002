package com.iucosoft.mylinksspringboot.repositories.bridge;

import com.iucosoft.mylinksspringboot.entities.Group;
import com.iucosoft.mylinksspringboot.entities.bridge.GroupMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
    @Query("SELECT u.group FROM GroupMember u WHERE u.member.id = :userId")
    Page<Group> findByUserMember(final @Param("userId") Long userId, final Pageable pageable);

    @Modifying
    @Query("DELETE FROM GroupMember gm WHERE  gm.group.id=:groupId")
    void deleteByGroupId(final @Param("groupId") Long groupId);

    @Modifying
    @Query("DELETE FROM GroupMember gm WHERE  gm.member.id=:memberId")
    void deleteByMemberId(final @Param("memberId") Long memberId);

    boolean existsByMemberIdAndAndGroupId(final Long memberId, final Long groupId);

    void deleteByGroupIdAndMemberId(final Long groupId, final Long memberId);

}
