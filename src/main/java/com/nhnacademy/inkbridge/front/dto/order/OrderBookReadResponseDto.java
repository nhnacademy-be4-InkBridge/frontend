package com.nhnacademy.inkbridge.front.dto.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * class: OrderBookReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/02/28
 */
@Getter
@RequiredArgsConstructor
public class OrderBookReadResponseDto {
    private final Long bookId;
    private final String bookTitle;
    private final Boolean isPackagable;
    private final Long regularPrice;
    private final Long price;
    private final Integer amount;
}
