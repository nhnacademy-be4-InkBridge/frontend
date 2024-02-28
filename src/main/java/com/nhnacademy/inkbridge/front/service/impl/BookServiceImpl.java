package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.BookAdaptor;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.service.BookService;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: BookServiceImpl.
 *
 * @author minm063
 * @version 2024/02/25
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookAdaptor bookAdaptor;

    public BookServiceImpl(BookAdaptor bookAdaptor) {
        this.bookAdaptor = bookAdaptor;
    }

    /**
     * {@inheritDoc}
     */
    public PageRequestDto<BooksAdminReadResponseDto> getBooksAdmin(Integer page, Integer size) {
        return bookAdaptor.getBooksAdmin(page, size);
    }

    /**
     * {@inheritDoc}
     */
    public BookAdminReadResponseDto getBook(Long bookId) {
        return bookAdaptor.getBookAdmin(bookId);
    }

    /**
     * {@inheritDoc}
     */
    public void createBook(MultipartFile thumbnail,
        BookAdminCreateRequestDto bookAdminCreateRequestDto)
        throws IOException {
        bookAdaptor.createBookAdmin(thumbnail, bookAdminCreateRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    public void updateBook(Long bookId, MultipartFile thumbnail,
        BookAdminUpdateRequestDto bookAdminUpdateRequestDto)
        throws IOException {
        bookAdaptor.updateBookAdmin(bookId, thumbnail, bookAdminUpdateRequestDto);
    }
}
