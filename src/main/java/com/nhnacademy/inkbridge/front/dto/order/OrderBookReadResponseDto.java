package com.nhnacademy.inkbridge.front.dto.order;

import lombok.Builder;
import lombok.Getter;

/**
 * class: OrderBookReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/13
 */
@Getter
public class OrderBookReadResponseDto {

    private Long bookId;
    private String thumbnail;
    private String bookTitle;
    private Long price;
    private Long regularPrice;
    private Integer amount;
    private Boolean isPackagable;

    @Builder
    public OrderBookReadResponseDto(Long bookId, String thumbnail, String bookTitle, Long price,
        Long regularPrice, Integer amount, Boolean isPackagable) {
        this.bookId = bookId;
        this.thumbnail = thumbnail;
        this.bookTitle = bookTitle;
        this.price = price;
        this.regularPrice = regularPrice;
        this.amount = amount;
        this.isPackagable = isPackagable;
    }
}
