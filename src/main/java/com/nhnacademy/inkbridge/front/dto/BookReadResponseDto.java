package com.nhnacademy.inkbridge.front.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;

/**
 * class: BookReadResponseDto.
 *
 * @author minm063
 * @version 2024/02/26
 */
@Getter
public class BookReadResponseDto {
    private String bookTitle;
    private String bookIndex;
    private String description;
    private LocalDate publicatedAt;
    private String isbn;
    private Long regularPrice;
    private Long price;
    private BigDecimal discountRatio;
    private Boolean isPackagable;
    private String publisherName;
}
