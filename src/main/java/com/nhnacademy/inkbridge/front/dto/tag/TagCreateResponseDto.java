package com.nhnacademy.inkbridge.front.dto.tag;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: TagCreateResponseDto.
 *
 * @author jeongbyeonghun
 * @version 2/15/24
 */
@Getter
@NoArgsConstructor
public class TagCreateResponseDto {

    private Long tagId;
    private String tagName;

    @Builder
    public TagCreateResponseDto(Long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }
}
