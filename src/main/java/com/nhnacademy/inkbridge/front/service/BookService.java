package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksAdminReadResponseDto;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: BookService.
 *
 * @author minm063
 * @version 2024/02/22
 */
public interface BookService {

    /**
     * 도서 전체 목록을 조회하는 메서드입니다.
     *
     * @return List - BooksAdminReadResponseDto
     */
    PageRequestDto<BooksAdminReadResponseDto> getBooksAdmin(Integer page, Integer size);

    /**
     * 도서 상세 정보를 조회하는 메서드입니다.
     *
     * @param bookId Long
     * @return BookAdminReadResponseDto
     */
    BookAdminReadResponseDto getBook(Long bookId);

    /**
     * 도서를 생성하는 메서드입니다.
     *
     * @param thumbnail                 MultipartFile
     * @param bookAdminCreateRequestDto BookAdminRequestDto
     */
    void createBook(MultipartFile thumbnail, BookAdminCreateRequestDto bookAdminCreateRequestDto)
        throws IOException;

    /**
     * 도서 정보를 수정하는 메서드입니다.
     *
     * @param thumbnail                 MultipartFile
     * @param bookAdminUpdateRequestDto BookAdminRequestDto
     */
    void updateBook(Long bookId, MultipartFile thumbnail,
        BookAdminUpdateRequestDto bookAdminUpdateRequestDto)
        throws IOException;

}
