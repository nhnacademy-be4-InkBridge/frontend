package com.nhnacademy.inkbridge.front.dto.book;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  class: BookRedisReadRequestDto.
 *
 *  @author minm063
 *  @version 2024/03/20
 */
@Getter
@Setter
@NoArgsConstructor
public class BookRedisReadResponseDto {

    private String bookTitle;
    private String thumbnailUrl;
    private Long price;
    private Long regularPrice;
    private BigDecimal discountRatio;

    @Builder
    public BookRedisReadResponseDto(String bookTitle, String thumbnailUrl, Long price,
        Long regularPrice, BigDecimal discountRatio) {
        this.bookTitle = bookTitle;
        this.thumbnailUrl = thumbnailUrl;
        this.price = price;
        this.regularPrice = regularPrice;
        this.discountRatio = discountRatio;
    }
}
