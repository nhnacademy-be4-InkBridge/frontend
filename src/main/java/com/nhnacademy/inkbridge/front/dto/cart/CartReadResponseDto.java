package com.nhnacademy.inkbridge.front.dto.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: CartReadResponseDto.
 *
 * @author minm063
 * @version 2024/03/11
 */
@Getter
@NoArgsConstructor
public class CartReadResponseDto {
    private Long bookId;
    private Integer amount;
}
