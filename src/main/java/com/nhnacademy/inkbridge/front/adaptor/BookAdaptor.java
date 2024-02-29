package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminDetailReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksAdminReadResponseDto;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: BookAdaptor.
 *
 * @author minm063
 * @version 2024/02/22
 */
public interface BookAdaptor {

    /**
     * 관리자 페이지에서 도서 전체 목록을 조회하는 메서드입니다.
     *
     * @return List - BooksAdminReadResponseDto
     */
    PageRequestDto<BooksAdminReadResponseDto> getBooksAdmin(Integer page, Integer size);

    /**
     * 관리자 페이지에서 도서 상세를 조회하는 메서드입니다.
     *
     * @param bookId Long
     * @return BookAdminReadResponseDto
     */
    BookAdminDetailReadResponseDto getBookAdmin(Long bookId);

    /**
     * 관리자 페이지에서 등록 페이지를 조회하는 메서드입니다. 등록에 필요한 데이터를 조회합니다.
     *
     * @return BookAdminReadResponseDto
     */
    BookAdminReadResponseDto getBookAdmin();

    /**
     * 관리자 페이지에서 도서를 생성하는 메서드입니다.
     *
     * @param thumbnail                 MultipartFile
     * @param bookAdminCreateRequestDto BookAdminRequestDto
     */
    void createBookAdmin(MultipartFile thumbnail,
        BookAdminCreateRequestDto bookAdminCreateRequestDto)
        throws IOException;

    /**
     * 관리자 페이지에서 생성한 도서 정보를 수정하는 메서드입니다.
     *
     * @param thumbnail                 MultipartFile
     * @param bookAdminUpdateRequestDto BookAdminRequestDto
     */
    void updateBookAdmin(Long bookId, MultipartFile thumbnail,
        BookAdminUpdateRequestDto bookAdminUpdateRequestDto)
        throws IOException;
}
