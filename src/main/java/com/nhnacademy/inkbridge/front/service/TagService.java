package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.tag.Tag;
import com.nhnacademy.inkbridge.front.dto.tag.TagCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.tag.TagUpdateRequestDto;
import java.util.List;

/**
 * class: TagService.
 *
 * @author jeongbyeonghun
 * @version 3/4/24
 */
public interface TagService {

    List<Tag> getTagList();

    void createTag(TagCreateRequestDto newTag);

    void deleteTag(Long tagId);

    void updateTag(Long tagId, TagUpdateRequestDto tagUpdateRequestDto);
}
