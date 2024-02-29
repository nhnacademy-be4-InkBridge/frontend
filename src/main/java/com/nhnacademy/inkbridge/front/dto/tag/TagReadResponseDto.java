package com.nhnacademy.inkbridge.front.dto.tag;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: TagReadResponseDto.
 *
 * @author jeongbyeonghun
 * @version 2/18/24
 */
@Getter
@NoArgsConstructor
public class TagReadResponseDto {

    private Long tagId;
    private String tagName;

    @Builder
    public TagReadResponseDto(Long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }
}
