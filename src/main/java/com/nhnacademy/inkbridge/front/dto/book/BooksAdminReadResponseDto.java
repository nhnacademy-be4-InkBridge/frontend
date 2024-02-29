package com.nhnacademy.inkbridge.front.dto.book;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: BooksReadResponse.
 *
 * @author minm063
 * @version 2024/02/22
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BooksAdminReadResponseDto {
    private Long bookId;
    private String bookTitle;
    private String authorName;
    private String publisherName;
    private String statusName;

    @Builder
    public BooksAdminReadResponseDto(Long bookId, String bookTitle, String authorName,
        String publisherName, String statusName) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.statusName = statusName;
    }
}
