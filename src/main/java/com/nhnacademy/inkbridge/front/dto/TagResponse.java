package com.nhnacademy.inkbridge.front.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * class: TagResponse.
 *
 * @author minm063
 * @version 2024/02/24
 */
@Getter
public class TagResponse {
    private Long tagId;
    private String tagName;

    @Builder
    public TagResponse(Long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }
}
