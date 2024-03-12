package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.cart.CartCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.cart.CartReadResponseDto;
import java.util.List;

/**
 * class: CartAdaptor.
 *
 * @author minm063
 * @version 2024/03/09
 */
public interface CartAdaptor {

    /**
     * 장바구니를 저장하는 메서드입니다.
     *
     * @param cartList CartCreateRequestDto
     */
    void saveCart(List<CartCreateRequestDto> cartList);

    /**
     * memberId에 따른 장바구니를 조회하는 메서드입니다.
     *
     * @param memberId Long
     * @return CartReadResponseDto
     */
    List<CartReadResponseDto> getCart(Long memberId);
}
