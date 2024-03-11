package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.CartAdaptor;
import com.nhnacademy.inkbridge.front.dto.cart.CartBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.cart.CartCreateRequestDto;
import com.nhnacademy.inkbridge.front.service.CartService;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private final RedisTemplate<String, Object> redisTemplateForCart;
    private final CartAdaptor cartAdaptor;

    /**
     * {@inheritDoc}
     */
    public CartServiceImpl(@Qualifier("redisTemplateForCart") RedisTemplate<String, Object> redisTemplateForCart, CartAdaptor cartAdaptor) {
        this.redisTemplateForCart = redisTemplateForCart;
        this.cartAdaptor = cartAdaptor;
    }

    @Override
    public void createCart(CartCreateRequestDto cartCreateRequestDto, String memberId) {
        log.info("create: {}", memberId);
        log.info("create: {}", cartCreateRequestDto.getBookId());
        HashOperations<String, String, Object> hashOperations = redisTemplateForCart.opsForHash();
        hashOperations.put(memberId, String.valueOf(cartCreateRequestDto.getBookId()), String.valueOf(cartCreateRequestDto.getAmount()));
    }

    @Override
    public Map<String, String> getCartRedis(String memberId) {
        HashOperations<String, String, String> hashOperations = redisTemplateForCart.opsForHash();
        log.info("info: {}", hashOperations.entries(memberId));
        return hashOperations.entries(memberId);
    }

    @Override
    public List<CartBookReadResponseDto> getCartBookInfo(Set<String> bookIdList) {
        log.info("book: {}", bookIdList.size());
        return cartAdaptor.getBook(bookIdList);
    }

    @Override
    public void deleteCartBook(String bookId, String memberId) {
        log.info("delete!!, {}, {}", memberId, bookId);
        HashOperations<String, Object, Object> hashOperations = redisTemplateForCart.opsForHash();
        hashOperations.delete(memberId, bookId);
    }
}
