package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.BookAdaptor;
import com.nhnacademy.inkbridge.front.dto.BookAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.BookAdminRequestDto;
import com.nhnacademy.inkbridge.front.dto.BookFileReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.BooksAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
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
    public void createBook(MultipartFile thumbnail, BookAdminRequestDto bookAdminRequestDto)
        throws IOException {
        bookAdaptor.createBookAdmin(thumbnail, bookAdminRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    public void updateBook(MultipartFile thumbnail, MultipartFile[] bookImages,
        BookAdminRequestDto bookAdminRequestDto) {
        bookAdaptor.updateBookAdmin(thumbnail, bookImages, bookAdminRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BookFileReadResponseDto uploadFile(MultipartFile image) throws IOException {
        return bookAdaptor.uploadFile(image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] loadFile(String fileName) {
        return bookAdaptor.loadFile(fileName);
    }
}
