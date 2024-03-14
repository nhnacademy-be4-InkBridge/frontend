package com.nhnacademy.inkbridge.front.dto.cart;

import lombok.Builder;
import lombok.Getter;

/**
 * class: CartCreateRequestDto.
 *
 * @author minm063
 * @version 2024/03/12
 */
@Getter
public class CartCreateRequestDto {

    private final Long memberId;
    private final Long bookId;
    private final Integer amount;

    @Builder
    public CartCreateRequestDto(Long memberId, Long bookId, Integer amount) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.amount = amount;
    }
}
