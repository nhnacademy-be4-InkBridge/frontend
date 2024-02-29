package com.nhnacademy.inkbridge.front.dto.book;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: BookFileReadResponseDto.
 *
 * @author minm063
 * @version 2024/02/26
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookFileReadResponseDto {
    private Long fileId;
    private String fileName;

    @Builder
    public BookFileReadResponseDto(Long fileId, String fileName) {
        this.fileId = fileId;
        this.fileName = fileName;
    }
}
