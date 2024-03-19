package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.BookAdaptor;
import com.nhnacademy.inkbridge.front.dto.book.BookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksByCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksReadResponseDto;
import com.nhnacademy.inkbridge.front.service.IndexService;
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

    public IndexServiceImpl(BookAdaptor bookAdaptor) {
        this.bookAdaptor = bookAdaptor;
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
        return bookAdaptor.getBook(bookId, memberId);
    }
}
