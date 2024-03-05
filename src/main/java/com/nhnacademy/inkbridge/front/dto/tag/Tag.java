package com.nhnacademy.inkbridge.front.dto.tag;

import lombok.Builder;
import lombok.Getter;

/**
 * class: Tag.
 *
 * @author jeongbyeonghun
 * @version 3/4/24
 */
@Getter
public class Tag {

    private Long tagId;
    private String tagName;
    @Builder
    public Tag(Long tagId, String tagName) {
        this.tagName = tagName;
        this.tagId = tagId;
    }

    public Tag(TagReadResponseDto dto){
        this.tagId = dto.getTagId();
        this.tagName = dto.getTagName();
    }
}
