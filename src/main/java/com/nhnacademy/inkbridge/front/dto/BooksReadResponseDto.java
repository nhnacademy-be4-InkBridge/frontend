package com.nhnacademy.inkbridge.front.dto;

import lombok.Getter;

/**
 * class: BookReadResponseDto.
 *
 * @author minm063
 * @version 2024/02/26
 */
@Getter
public class BooksReadResponseDto {
    private Long bookId;
    private String bookTitle;
    private String authorName;
    private String publisherName;
    private Long price;
}
