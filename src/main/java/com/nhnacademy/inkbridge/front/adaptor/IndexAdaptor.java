package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.book.BookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksReadResponseDto;
import java.util.List;

/**
 * class: IndexAdaptor.
 *
 * @author minm063
 * @version 2024/02/26
 */
public interface IndexAdaptor {

    List<BooksReadResponseDto> getBooks();

    BookReadResponseDto getBook(Long bookId);
}
