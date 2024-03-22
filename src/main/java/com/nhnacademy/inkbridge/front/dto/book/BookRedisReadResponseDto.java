package com.nhnacademy.inkbridge.front.dto.book;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  class: BookRedisReadRequestDto.
 *
 *  @author minm063
 *  @version 2024/03/20
 */
@Getter
@NoArgsConstructor
public class BookRedisReadResponseDto {

    private String bookTitle;
    private String thumbnailUrl;
    private Long price;
    private Long regularPrice;
    private BigDecimal discountRatio;
}
