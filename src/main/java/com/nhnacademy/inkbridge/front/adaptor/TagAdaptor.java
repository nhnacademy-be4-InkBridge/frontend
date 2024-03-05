package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.tag.TagCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.tag.TagReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.tag.TagUpdateRequestDto;
import java.util.List;

/**
 * class: TagAdaptor.
 *
 * @author jeongbyeonghun
 * @version 3/4/24
 */
public interface TagAdaptor {

    List<TagReadResponseDto> getTagList();

    void createTag(TagCreateRequestDto newTag);

    void deleteTag(Long tagId);

    void updateTag(Long tagId, TagUpdateRequestDto tagUpdateRequestDto);
}
