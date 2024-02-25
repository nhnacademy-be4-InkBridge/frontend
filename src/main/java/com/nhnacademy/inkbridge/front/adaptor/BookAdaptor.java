package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.BookAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.BookAdminRequestDto;
import com.nhnacademy.inkbridge.front.dto.BooksAdminReadResponseDto;
import java.io.IOException;
import java.util.List;
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
    List<BooksAdminReadResponseDto> getBooksAdmin();

    /**
     * 관리자 페이지에서 도서 상세를 조회하는 메서드입니다.
     *
     * @param bookId Long
     * @return BookAdminReadResponseDto
     */
    BookAdminReadResponseDto getBookAdmin(Long bookId);

    /**
     * 관리자 페이지에서 도서를 생성하는 메서드입니다.
     *
     * @param thumbnail MultipartFile
     * @param bookImages MultipartFile[]
     * @param bookAdminRequestDto BookAdminRequestDto
     */
    void createBookAdmin(MultipartFile thumbnail, MultipartFile[] bookImages,
        BookAdminRequestDto bookAdminRequestDto)
        throws IOException;

    /**
     * 관리자 페이지에서 생성한 도서 정보를 수정하는 메서드입니다.
     *
     * @param thumbnail MultipartFile
     * @param bookImages MultipartFile[]
     * @param bookAdminRequestDto BookAdminRequestDto
     */
    void updateBookAdmin(MultipartFile thumbnail, MultipartFile[] bookImages,
        BookAdminRequestDto bookAdminRequestDto);

}
