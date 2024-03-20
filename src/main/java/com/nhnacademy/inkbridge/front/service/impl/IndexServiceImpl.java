package com.nhnacademy.inkbridge.front.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.inkbridge.front.adaptor.BookAdaptor;
import com.nhnacademy.inkbridge.front.dto.book.BookDetailReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BookRedisReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksReadResponseDto;
import com.nhnacademy.inkbridge.front.service.IndexService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
        ObjectMapper objectMapper = new ObjectMapper();

        BookReadResponseDto book = bookAdaptor.getBook(bookId, memberId);
        BookDetailReadResponseDto bookDetail = book.getBookDetailReadResponseDto();

        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();

        if (valueOperations.get(String.valueOf(bookId)) != null) {
            // 레디스에 도서 번호가 존재하면 view에 +1
            valueOperations.increment(bookId + "view", 1);
        } else {
            // 존재하지 않으면 레디스에 도서 정보 저장
            try {
                redisTemplate.opsForValue()
                    .set(String.valueOf(bookId), objectMapper.writeValueAsString(
                        BookRedisReadResponseDto.builder().bookTitle(bookDetail.getBookTitle())
                            .thumbnailUrl(bookDetail.getThumbnail()).price(
                                bookDetail.getPrice()).regularPrice(bookDetail.getRegularPrice())
                            .discountRatio(bookDetail.getDiscountRatio()).build()));
                redisTemplate.opsForValue()
                    .set(bookId + "view", bookDetail.getView());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        return book;
    }
}
