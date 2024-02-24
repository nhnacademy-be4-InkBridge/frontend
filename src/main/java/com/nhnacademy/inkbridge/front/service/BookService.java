package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.adaptor.BookAdaptor;
import com.nhnacademy.inkbridge.front.dto.BookAdminReadResponse;
import com.nhnacademy.inkbridge.front.dto.BookAdminRequest;
import com.nhnacademy.inkbridge.front.dto.BooksAdminReadResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: BookService.
 *
 * @author minm063
 * @version 2024/02/22
 */
@Service
public class BookService {

    private final BookAdaptor bookAdaptor;

    public BookService(BookAdaptor bookAdaptor) {
        this.bookAdaptor = bookAdaptor;
    }

    public List<BooksAdminReadResponse> getBooks() {
        return bookAdaptor.getBooksAdmin();
    }

    public BookAdminReadResponse getBook(Long bookId) {
        return bookAdaptor.getBookAdmin(bookId);
    }


    public void createBook(MultipartFile thumbnail, MultipartFile[] bookImages,
        BookAdminRequest bookAdminRequest) throws IOException {
        bookAdaptor.createBookAdmin(thumbnail, bookImages, bookAdminRequest);
    }

    public void updateBook(MultipartFile thumbnail, MultipartFile[] bookImages,
        BookAdminRequest bookAdminRequest) {
        bookAdaptor.updateBookAdmin(thumbnail, bookImages, bookAdminRequest);
    }
}
