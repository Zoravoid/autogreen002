package com.iucosoft.mylinksspringboot.service.bridge;

import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.Tag;
import com.iucosoft.mylinksspringboot.entities.bridge.LinkOwner;
import com.iucosoft.mylinksspringboot.entities.bridge.LinkTag;
import com.iucosoft.mylinksspringboot.service.OperationIntf;

import java.util.List;
import java.util.Set;

public interface LinkTagService extends OperationIntf<LinkTag, Long> {
    List<LinkTag> saveLinkTagConnection(Link link, List<Tag> updatedTags);

    List<Tag> getTagsByLink(Link link);

    void deleteAllByLinkId(Long linkId);
}
