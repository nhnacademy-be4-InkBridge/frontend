package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.cart.CartCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.cart.CartReadResponseDto;

/**
 * class: CartService.
 *
 * @author minm063
 * @version 2024/03/08
 */
public interface CartService {

    /**
     * @param cartCreateRequestDto CartCreateRequestDto
     * @param memberId             memberId or UUID
     */
    void createCart(CartCreateRequestDto cartCreateRequestDto, String memberId);

    CartReadResponseDto getCart(String memberId);
}
