package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.BookAdaptor;
import com.nhnacademy.inkbridge.front.dto.book.BookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksReadResponseDto;
import com.nhnacademy.inkbridge.front.service.IndexService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * class: IndexServiceImpl.
 *
 * @author minm063
 * @version 2024/02/26
 */
@Service
public class IndexServiceImpl implements IndexService {

    private final BookAdaptor bookAdaptor;
    private final RedisTemplate<String, Object> redisTemplate;


    public IndexServiceImpl(BookAdaptor bookAdaptor,
        @Qualifier(value = "redisTemplateForBook") RedisTemplate<String, Object> redisTemplate) {
        this.bookAdaptor = bookAdaptor;
        this.redisTemplate = redisTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BooksReadResponseDto getBooks(Long page) {
        return bookAdaptor.getBooks(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BooksReadResponseDto getBooksByCategory(Long page, Long categoryId) {
        return bookAdaptor.getBooksByCategory(page, categoryId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BookReadResponseDto getBook(Long bookId, Long memberId) {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        Map<String, Object> entries = hashOperations.entries(String.valueOf(memberId));

        return bookAdaptor.getBook(bookId, memberId);
    }
}
