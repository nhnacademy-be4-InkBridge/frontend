package com.nhnacademy.inkbridge.front.dto.book;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: BookAdminReadResponse.
 *
 * @author minm063
 * @version 2024/02/22
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookAdminReadResponseDto {
    private String bookTitle;
    private String bookIndex;
    private String description;
    private LocalDate publicatedAt;
    private String isbn;
    private Long regularPrice;
    private Long price;
    private BigDecimal discountRatio;
    private Integer stock;
    private Boolean isPackagable;
    private Long publisherId;
    private Long authorId;
    private Long statusId;
    private String url;
}
