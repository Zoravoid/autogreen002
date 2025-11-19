package com.iucosoft.mylinksspringboot.service.bridge;

import com.iucosoft.mylinksspringboot.dto.link.LinkDTO;
import com.iucosoft.mylinksspringboot.entities.Category;
import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.bridge.LinkCategory;
import com.iucosoft.mylinksspringboot.service.OperationIntf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LinkCategoryService extends OperationIntf<LinkCategory, Long> {

    void deleteByCategoryId(Long categoryId);

    void deleteByLinkId(Long linkId);

    Page<LinkDTO> getLinksByCategoryId(Long categoryId, Pageable pageable);

    List<LinkCategory> getLinkCategoryListByLinkId(Long linkId);

    List<LinkCategory> getLinkCategoryListByCategoryId(Long categoryId);

    boolean existsByCategoryIdAndLinkId(final Long categoryId, final Long linkId);

    void removeLinkFomCategory(Long categoryId, Link link);
}
