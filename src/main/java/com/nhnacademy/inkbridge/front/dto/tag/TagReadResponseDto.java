package com.nhnacademy.inkbridge.front.dto.tag;

import lombok.Builder;
import lombok.Getter;

/**
 * class: TagReadResponseDto.
 *
 * @author jeongbyeonghun
 * @version 2/18/24
 */
@Getter
public class TagReadResponseDto {

    private  final Long tagId;
    private  final String tagName;

    @Builder
    public TagReadResponseDto(Long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }
}
