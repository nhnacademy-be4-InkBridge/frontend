package com.nhnacademy.inkbridge.front.dto.cart;

import lombok.Builder;
import lombok.Getter;

/**
 * class: CartCreateRequestDto.
 *
 * @author minm063
 * @version 2024/03/08
 */
@Getter
public class CartRedisCreateRequestDto {

    private final Long bookId;
    private final Long amount;

    @Builder
    public CartRedisCreateRequestDto(Long bookId, Long amount) {
        this.bookId = bookId;
        this.amount = amount;
    }
}
