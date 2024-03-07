package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksReadResponseDto;

/**
 * class: IndexService.
 *
 * @author minm063
 * @version 2024/02/26
 */
public interface IndexService {

    /**
     * 도서 목록을 조회하는 메서드입니다.
     *
     * @return BooksReadResponseDto page
     */
    PageRequestDto<BooksReadResponseDto> getBooks();

    /**
     * 도서 상세 정보를 조회하는 메서드입니다.
     *
     * @param bookId Long
     * @return BookReadResponseDto
     */
    BookReadResponseDto getBook(Long bookId);
}
