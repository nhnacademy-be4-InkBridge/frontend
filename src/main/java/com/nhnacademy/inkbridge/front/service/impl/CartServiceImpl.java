package com.nhnacademy.inkbridge.front.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.inkbridge.front.adaptor.BookAdaptor;
import com.nhnacademy.inkbridge.front.dto.book.BookRedisReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.cart.CartBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.cart.CartRedisCreateRequestDto;
import com.nhnacademy.inkbridge.front.service.CartService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
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

    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisTemplate<String, Object> redisTemplateForBook;
    private final BookAdaptor bookAdaptor;
    private static final int A_WEEK = 7;

    public CartServiceImpl(RedisTemplate<String, Object> redisTemplate,
        @Qualifier(value = "redisTemplateForBook") RedisTemplate<String, Object> redisTemplateForBook,
        BookAdaptor bookAdaptor) {
        this.redisTemplate = redisTemplate;
        this.redisTemplateForBook = redisTemplateForBook;
        this.bookAdaptor = bookAdaptor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Long> getCartRedis(String memberId) {

        // 장바구니 가져옴 -> BookId로 정보 조회 -> 레디스에 정보 없으면 Db에서 조회
        HashOperations<String, String, Long> hashOperations = redisTemplate.opsForHash();

        return hashOperations.entries(memberId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, BookRedisReadResponseDto> getBookInfo(ArrayList<String> keys) {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Object> bookInfos = redisTemplateForBook.opsForValue()
            .multiGet(keys);
        bookInfos = Objects.isNull(bookInfos) ? Collections.emptyList() : bookInfos;

        Map<String, BookRedisReadResponseDto> book = new ConcurrentHashMap<>();
        Set<String> bookNotInRedis = new HashSet<>();
        for (int i = 0; i < bookInfos.size(); i++) {
            String key = keys.get(i);
            Object rawValue = bookInfos.get(i);
            if (rawValue != null) {
                try {
                    BookRedisReadResponseDto value = objectMapper.readValue(
                        (String) rawValue, BookRedisReadResponseDto.class);
                    book.put(key, value);
                } catch (JsonProcessingException e) {
                    log.error("error: {}", e.getMessage());
                }
            } else {
                bookNotInRedis.add(keys.get(i));
            }
        }

        if (!bookNotInRedis.isEmpty()) {
            List<CartBookReadResponseDto> cartBookInfo = getCartBookInfo(bookNotInRedis);
            cartBookInfo.forEach(
                cartBookReadResponseDto -> {
                    BookRedisReadResponseDto dto = BookRedisReadResponseDto.builder().bookTitle(
                            cartBookReadResponseDto.getBookTitle()).thumbnailUrl(
                            cartBookReadResponseDto.getThumbnail()).price(
                            cartBookReadResponseDto.getPrice()).regularPrice(
                            cartBookReadResponseDto.getRegularPrice()).discountRatio(
                            cartBookReadResponseDto.getDiscountRatio())
                        .build();
                    try {
                        redisTemplateForBook.opsForValue()
                            .set(String.valueOf(cartBookReadResponseDto.getBookId()),
                                objectMapper.writeValueAsString(dto));
                    } catch (JsonProcessingException e) {
                        log.error("error: {}", e.getMessage());
                    }
                    book.put(String.valueOf(cartBookReadResponseDto.getBookId()), dto);
                });
        }

        return book;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CartBookReadResponseDto> getCartBookInfo(Set<String> bookIdList) {
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
        redisTemplate.expire(memberId, A_WEEK, TimeUnit.DAYS);
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
    public void updateCartBook(String memberId, String bookId, String amount) {
        redisTemplate.opsForHash().put(memberId, bookId, amount);
    }
}
