package com.nhnacademy.inkbridge.front.dto.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: CartCreateRequestDto.
 *
 * @author minm063
 * @version 2024/03/08
 */
@Getter
@NoArgsConstructor
public class CartRedisCreateRequestDto {

    private Long bookId;
    private Long amount;
}
