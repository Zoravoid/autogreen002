package com.iucosoft.mylinksspringboot.service;

import com.iucosoft.mylinksspringboot.dto.MessageDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkDTO;
import com.iucosoft.mylinksspringboot.entities.Category;
import com.iucosoft.mylinksspringboot.entities.Group;
import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.entities.Visibility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface LinkService extends OperationIntf<Link, Long> {

    Link createLink(Link linkToSave, Set<String> tagTitles, List<Long> categoryIds);

    Link update(long id, Link newLink, Set<String> tagTitles, List<Long> categoryIds);

    Page<Link> getAllLinksByOwnerId(Long ownerId, Pageable pageable);

    User getOwnerByLinkId(Long linkId);

    Group getGroupByLinkId(Long linkId);

    Page<Category> getCategoriesByLinkId(Long linkId, Pageable pageable);

    MessageDTO delete(Long linkId);

    Page<Link> getFilteredLinks(String title, String url, Visibility visibility, List<Long> categoryIds, List<String> tagTitles, Pageable pageable);


}
