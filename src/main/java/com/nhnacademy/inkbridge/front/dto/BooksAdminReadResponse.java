package com.nhnacademy.inkbridge.front.dto;

import lombok.AccessLevel;
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
public class BooksAdminReadResponse {
    private String bookTitle;
    private String authorName;
    private String publisherName;
    private String statusName;
}
