package com.nhnacademy.inkbridge.front.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.inkbridge.front.adaptor.BookAdaptor;
import com.nhnacademy.inkbridge.front.dto.book.BookDetailReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BookRedisReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksByCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksReadResponseDto;
import com.nhnacademy.inkbridge.front.service.IndexService;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class IndexServiceImpl implements IndexService {

    private final BookAdaptor bookAdaptor;
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String VIEW_NAME_PATTERN = "view:";


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
    public BooksByCategoryReadResponseDto getBooksByCategory(Long page, Long categoryId) {
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

        if (Objects.isNull(valueOperations.get(String.valueOf(bookId)))) {
            // 존재하지 않으면 레디스에 도서 정보 저장
            try {
                redisTemplate.opsForValue()
                    .set(String.valueOf(bookId), objectMapper.writeValueAsString(
                        BookRedisReadResponseDto.builder().bookTitle(bookDetail.getBookTitle())
                            .thumbnailUrl(bookDetail.getThumbnail()).price(
                                bookDetail.getPrice()).regularPrice(bookDetail.getRegularPrice())
                            .discountRatio(bookDetail.getDiscountRatio()).build()));
            } catch (JsonProcessingException e) {
                log.error("error : {}", e.getMessage());
            }
        }

        if (Objects.isNull(valueOperations.get(VIEW_NAME_PATTERN + bookId))) {
            redisTemplate.opsForValue()
                .set(VIEW_NAME_PATTERN + bookId, bookDetail.getView());

        }
        valueOperations.increment(VIEW_NAME_PATTERN + bookId, 1);

        return book;
    }
}
