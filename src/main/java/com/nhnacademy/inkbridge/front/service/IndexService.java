package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.BookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.BooksReadResponseDto;
import java.util.List;

/**
 * class: IndexService.
 *
 * @author minm063
 * @version 2024/02/26
 */
public interface IndexService {
    List<BooksReadResponseDto> getBooks();

    BookReadResponseDto getBook(Long bookId);
}