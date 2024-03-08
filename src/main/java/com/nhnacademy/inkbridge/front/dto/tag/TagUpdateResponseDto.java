package com.nhnacademy.inkbridge.front.dto.tag;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: TagUpdateResponseDto.
 *
 * @author jeongbyeonghun
 * @version 2/15/24
 */

@Getter
@NoArgsConstructor
public class TagUpdateResponseDto {

    private Long tagId;
    private String tagName;


    @Builder
    public TagUpdateResponseDto(Long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }
}
