package com.nhnacademy.inkbridge.front.dto.book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
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
public class BookDetailReadResponseDto {

    private String bookTitle;
    private String bookIndex;
    private String description;
    private LocalDate publicatedAt;
    private String isbn;
    private Long regularPrice;
    private Long price;
    private BigDecimal discountRatio;
    private Boolean isPackagable;
    private String thumbnail;
    private String statusName;
    private Long publisherId;
    private String publisherName;
    private Map<Long, String> authors;
    private Long wish;
    private Long view;
    private Set<String> fileUrl;
    private Set<String> tagName;
    private Set<String> categoryName;
}
