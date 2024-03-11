package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.cart.CartBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.cart.CartCreateRequestDto;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    /**
     *
     * @param memberId nullable String
     * @return CartReadResponseDto
     */
    Map<String, String> getCartRedis(String memberId);

    List<CartBookReadResponseDto> getCartBookInfo(Set<String> bookIdList);

    void deleteCartBook(String bookId, String memberId);
}
