package com.nhnacademy.inkbridge.front.dto.book;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: BookFileReadResponseDto.
 *
 * @author minm063
 * @version 2024/02/26
 */
@Getter
@NoArgsConstructor
public class BookFileReadResponseDto {

    private Long fileId;
    private String fileName;
}
