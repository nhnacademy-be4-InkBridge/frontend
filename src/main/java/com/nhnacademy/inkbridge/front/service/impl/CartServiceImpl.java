package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.CartAdaptor;
import com.nhnacademy.inkbridge.front.dto.cart.CartBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.cart.CartCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.cart.CartReadResponseDto;
import com.nhnacademy.inkbridge.front.service.CartService;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * class: CartServiceImpl.
 *
 * @author minm063
 * @version 2024/03/08
 */
@Slf4j
@Service
public class CartServiceImpl implements CartService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final CartAdaptor cartAdaptor;


    public CartServiceImpl(RedisTemplate<String, Object> redisTemplate, CartAdaptor cartAdaptor) {
        this.redisTemplate = redisTemplate;
        this.cartAdaptor = cartAdaptor;
    }

    @Override
    public void createCart(CartCreateRequestDto cartCreateRequestDto, String memberId) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(memberId, String.valueOf(cartCreateRequestDto.getBookId()),
            String.valueOf(cartCreateRequestDto.getAmount()));
    }

    @Override
    public CartReadResponseDto getCart(String memberId) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        Map<String, String> bookId = hashOperations.entries(memberId);// bookId, amount

        List<CartBookReadResponseDto> bookInfo = cartAdaptor.getBook(bookId.keySet());
        return CartReadResponseDto.builder().bookInfo(bookInfo).bookId(bookId).build();
    }

}
