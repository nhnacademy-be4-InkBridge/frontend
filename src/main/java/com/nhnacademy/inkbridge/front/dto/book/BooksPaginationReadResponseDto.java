package com.nhnacademy.inkbridge.front.dto.book;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: BooksReadResponseDto.
 *
 * @author minm063
 * @version 2024/02/15
 */
@Getter
@NoArgsConstructor
public class BooksPaginationReadResponseDto {

    private Long bookId;
    private String bookTitle;
    private Long price;
    private String publisherName;
    private String fileUrl;
}
