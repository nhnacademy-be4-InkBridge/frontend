package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.BookAdaptor;
import com.nhnacademy.inkbridge.front.dto.cart.CartBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.cart.CartRedisCreateRequestDto;
import com.nhnacademy.inkbridge.front.service.CartService;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
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
    private final BookAdaptor bookAdaptor;

    public CartServiceImpl(RedisTemplate<String, Object> redisTemplate, BookAdaptor bookAdaptor) {
        this.redisTemplate = redisTemplate;
        this.bookAdaptor = bookAdaptor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getCartRedis(String memberId) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        log.info("info: {}", hashOperations.entries(memberId));
        return hashOperations.entries(memberId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CartBookReadResponseDto> getCartBookInfo(Set<String> bookIdList) {
        log.info("list: {}", bookIdList);
        return bookAdaptor.getBook(bookIdList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCart(CartRedisCreateRequestDto cartRedisCreateRequestDto, String memberId) {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(memberId, String.valueOf(cartRedisCreateRequestDto.getBookId()),
            String.valueOf(cartRedisCreateRequestDto.getAmount()));
        redisTemplate.expire(memberId, 7, TimeUnit.DAYS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCartForMember(CartRedisCreateRequestDto cartRedisCreateRequestDto,
        String memberId) {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(memberId, String.valueOf(cartRedisCreateRequestDto.getBookId()),
            String.valueOf(cartRedisCreateRequestDto.getAmount()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCartBook(String bookId, String memberId) {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(memberId, bookId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCartBook(String bookId, String amount) {
        String memberId = String.valueOf(CommonUtils.getMemberId());
        redisTemplate.opsForHash().put(memberId, bookId, amount);
    }
}
