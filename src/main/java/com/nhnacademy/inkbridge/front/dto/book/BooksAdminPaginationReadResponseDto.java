package com.nhnacademy.inkbridge.front.dto.book;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: BooksAdminReadResponse.
 *
 * @author minm063
 * @version 2024/02/15
 */
@Getter
@NoArgsConstructor
public class BooksAdminPaginationReadResponseDto {

    private Long bookId;
    private String bookTitle;
    private String publisherName;
    private String statusName;
}
