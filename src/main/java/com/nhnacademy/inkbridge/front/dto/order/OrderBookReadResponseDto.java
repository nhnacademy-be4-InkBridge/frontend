package com.nhnacademy.inkbridge.front.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * class: OrderPageResponseDto.
 *
 * @author jangjaehun
 * @version 2024/02/27
 */
@Getter
@AllArgsConstructor
public class OrderBookReadResponseDto {

    private Long bookId;
    private String bookTitle;
    private Long regularPrice;
    private Long price;
    private Integer amount;
    private boolean isPackable;

}