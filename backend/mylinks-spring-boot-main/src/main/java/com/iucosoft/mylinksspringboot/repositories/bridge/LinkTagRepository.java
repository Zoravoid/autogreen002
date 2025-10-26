package com.iucosoft.mylinksspringboot.repositories.bridge;

import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.Tag;
import com.iucosoft.mylinksspringboot.entities.bridge.LinkTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkTagRepository extends JpaRepository<LinkTag, Long> {
    List<LinkTag> findByLinkId(Long linkId);

    void deleteAllByLinkId(Long linkId);
}
