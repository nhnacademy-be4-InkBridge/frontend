package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.adaptor.BookAdaptor;
import com.nhnacademy.inkbridge.front.dto.BooksAdminReadResponse;
import java.util.List;
import org.springframework.stereotype.Service;

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
}
