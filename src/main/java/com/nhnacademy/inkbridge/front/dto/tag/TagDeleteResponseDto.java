package com.nhnacademy.inkbridge.front.dto.tag;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: TagDeleteResponseDto.
 *
 * @author jeongbyeonghun
 * @version 2/15/24
 */
@Getter
@NoArgsConstructor
public class TagDeleteResponseDto {

    private String message;

    @Builder
    public TagDeleteResponseDto(String message) {
        this.message = message;
    }
}
