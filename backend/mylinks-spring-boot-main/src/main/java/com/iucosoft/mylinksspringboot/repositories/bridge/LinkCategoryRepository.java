package com.iucosoft.mylinksspringboot.repositories.bridge;


import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.bridge.LinkCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkCategoryRepository extends JpaRepository<LinkCategory, Long> {

    @Query("SELECT lc.link FROM LinkCategory lc WHERE lc.category.id = :categoryId")
    Page<Link> findLinksByCategoryId(final @Param("categoryId") Long categoryId, Pageable pageable);

    List<LinkCategory> findLinkCategoriesByCategoryId(Long categoryId);
    List<LinkCategory> findLinkCategoriesByLinkId(Long linkId);

    @Modifying
    @Query("DELETE FROM LinkCategory lc WHERE lc.category.id =:categoryId")
    void deleteByCategoryId (final @Param("categoryId") Long categoryId);

    @Modifying
    @Query("DELETE FROM LinkCategory lc WHERE  lc.link.id=:linkId")
    void deleteByLinkId(final @Param("linkId") Long linkId);

    boolean existsByCategoryIdAndLinkId(final Long categoryId, final Long linkId);

    void deleteByCategoryIdAndLinkId(final Long categoryId, final Long linkId);
}
