package com.nhnacademy.inkbridge.front.dto.bookstatus;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: BookStatusReadResponseDto.
 *
 * @author minm063
 * @version 2024/02/27
 */
@Getter
@NoArgsConstructor
public class BookStatusReadResponseDto {

    private Long statusId;
    private String statusName;

    @Builder
    public BookStatusReadResponseDto(Long statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }
}
