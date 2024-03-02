package com.nhnacademy.inkbridge.front.dto.book;

import lombok.Builder;
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
    private String authorName;
    private String publisherName;
    private Long price;
    private String fileUrl;

    @Builder
    public BooksReadResponseDto(Long bookId, String bookTitle, String authorName,
        String publisherName,
        Long price, String fileUrl) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.price = price;
        this.fileUrl = fileUrl;
    }
}
