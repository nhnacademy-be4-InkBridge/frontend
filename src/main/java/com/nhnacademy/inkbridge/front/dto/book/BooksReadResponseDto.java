package com.nhnacademy.inkbridge.front.dto.book;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: BookReadResponseDto.
 *
 * @author minm063
 * @version 2024/02/26
 */
@Getter
@NoArgsConstructor
public class BooksReadResponseDto {

    private Long bookId;
    private String bookTitle;
    private Long price;
    private String publisherName;
    private String authorName;
    private String fileUrl;
}
