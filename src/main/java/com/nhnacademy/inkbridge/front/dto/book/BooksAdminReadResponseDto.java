package com.nhnacademy.inkbridge.front.dto.book;

import java.util.List;
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
@NoArgsConstructor
public class BooksAdminReadResponseDto {

    private Long bookId;
    private String bookTitle;
    private List<String> authorName;
    private String publisherName;
    private String statusName;

    @Builder
    public BooksAdminReadResponseDto(Long bookId, String bookTitle,
        List<String> authorName, String publisherName, String statusName) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.statusName = statusName;
    }
}
