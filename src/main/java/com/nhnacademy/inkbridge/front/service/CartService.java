package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.cart.CartBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.cart.CartRedisCreateRequestDto;
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
     * redis에서 장바구니를 조회하는 메서드입니다.
     *
     * @param memberId String
     * @return Map
     */
    Map<String, String> getCartRedis(String memberId);

    /**
     * 도서 아이디에 따른 도서 상세 정보를 조회하는 메서드입니다.
     *
     * @param bookIdList Set
     * @return CartBookReadResponseDto
     */
    List<CartBookReadResponseDto> getCartBookInfo(Set<String> bookIdList);

    /**
     * 비회원용 장바구니를 생성하는 메서드입니다.
     *
     * @param cartRedisCreateRequestDto CartCreateRequestDto
     * @param memberId                  UUID
     */
    void createCart(CartRedisCreateRequestDto cartRedisCreateRequestDto, String memberId);

    /**
     * 회원용 장바구니를 생성하는 메서드입니다.
     *
     * @param cartRedisCreateRequestDto CartRedisCreateRequestDto
     * @param memberId                  String
     */
    void createCartForMember(CartRedisCreateRequestDto cartRedisCreateRequestDto, String memberId);

    /**
     * 레디스의 장바구니에서 bookId와 일치하는 도서를 삭제하는 메서드입니다.
     *
     * @param bookId   String
     * @param memberId String
     */
    void deleteCartBook(String bookId, String memberId);

    /**
     * 레디스의 장바구니에서 bookId와 일치하는 도서의 수량을 수정하는 메서드입니다.
     *
     * @param bookId String
     * @param amount String
     */
    void updateCartBook(String bookId, String amount);
}
