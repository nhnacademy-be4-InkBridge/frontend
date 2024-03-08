package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.IndexAdaptor;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BookReadResponseDto;
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

    private final IndexAdaptor indexAdaptor;

    public IndexServiceImpl(IndexAdaptor indexAdaptor) {
        this.indexAdaptor = indexAdaptor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageRequestDto<BooksReadResponseDto> getBooks(Long page) {
        return indexAdaptor.getBooks(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BookReadResponseDto getBook(Long bookId) {
        return indexAdaptor.getBook(bookId);
    }
}
