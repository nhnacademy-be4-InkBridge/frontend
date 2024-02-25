package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.BookAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.BookAdminRequestDto;
import com.nhnacademy.inkbridge.front.dto.BooksAdminReadResponseDto;
import java.io.IOException;
import java.util.List;
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
    List<BooksAdminReadResponseDto> getBooks();

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
     * @param thumbnail MultipartFile
     * @param bookImages MultipartFile[]
     * @param bookAdminRequestDto BookAdminRequestDto
     */
    void createBook(MultipartFile thumbnail, MultipartFile[] bookImages,
        BookAdminRequestDto bookAdminRequestDto) throws IOException;

    /**
     * 도서 정보를 수정하는 메서드입니다.
     *
     * @param thumbnail MultipartFile
     * @param bookImages MultipartFile[]
     * @param bookAdminRequestDto BookAdminRequestDto
     */
    void updateBook(MultipartFile thumbnail, MultipartFile[] bookImages,
        BookAdminRequestDto bookAdminRequestDto);
}
