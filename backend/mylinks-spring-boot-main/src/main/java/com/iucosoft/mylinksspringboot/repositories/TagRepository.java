package com.iucosoft.mylinksspringboot.repositories;

import com.iucosoft.mylinksspringboot.entities.Tag;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query(value = "SELECT t FROM Tag t WHERE t.title IN :titles")
    List<Tag> findByTitleInExactCase(@Param("titles") List<String> titles);
}
