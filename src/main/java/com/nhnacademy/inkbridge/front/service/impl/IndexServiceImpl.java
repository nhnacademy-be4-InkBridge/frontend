package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.IndexAdaptor;
import com.nhnacademy.inkbridge.front.dto.book.BookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksReadResponseDto;
import com.nhnacademy.inkbridge.front.service.IndexService;
import java.util.List;
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

    @Override
    public List<BooksReadResponseDto> getBooks() {
        return indexAdaptor.getBooks();
    }

    @Override
    public BookReadResponseDto getBook(Long bookId) {
        return indexAdaptor.getBook(bookId);
    }
}
