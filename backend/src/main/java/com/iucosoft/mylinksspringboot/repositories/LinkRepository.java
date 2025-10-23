package com.iucosoft.mylinksspringboot.repositories;

import com.iucosoft.mylinksspringboot.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Transactional
public interface LinkRepository extends JpaRepository<Link, Long> {
    @Query(value = "SELECT lo.owner FROM LinkOwner lo WHERE lo.link.id = :linkId ")
    User getOwnerByLinkId(final @Param("linkId") Long linkId);

    @Query("SELECT gl.group FROM GroupLink gl WHERE gl.link.id = :linkId")
    Group getGroupByLinkId(final @Param("linkId") Long linkId);

    @Query("SELECT lc.category FROM LinkCategory lc WHERE lc.link.id = :linkId")
    Page<Category> getCategoriesByLinkId(final @Param("linkId") Long linkId, Pageable pageable);

    @Query("SELECT DISTINCT l FROM Link l " +
            "LEFT JOIN l.owner o " +
            "LEFT JOIN LinkCategory lc ON lc.link = l " +
            "LEFT JOIN lc.category c " +
            "LEFT JOIN LinkTag lt ON lt.link = l " +
            "LEFT JOIN lt.tag t " +
            "WHERE (:title IS NULL OR l.title LIKE %:title%) " +
            "AND (:url IS NULL OR l.url LIKE %:url%) " +
            "AND (:visibility IS NULL OR l.visibility = :visibility) " +
            "AND (COALESCE(:categoryIds, NULL) IS NULL OR c.id IN :categoryIds) " +
            "AND (COALESCE(:tagTitles, NULL) IS NULL OR t.title IN :tagTitles)")
    Page<Link> getLinksByFilterParams(@Param("title") String title,
                                      @Param("url") String url,
                                      @Param("visibility") Visibility visibility,
                                      @Param("categoryIds") List<Long> categoryIds,
                                      @Param("tagTitles") List<String> tagTitles,
                                      Pageable pageable);
}
