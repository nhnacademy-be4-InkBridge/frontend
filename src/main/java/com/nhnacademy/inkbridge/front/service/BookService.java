package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.BookAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.BookAdminRequestDto;
import com.nhnacademy.inkbridge.front.dto.BookFileReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.BooksAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import java.io.IOException;
import org.springframework.core.io.Resource;
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
     * @param thumbnail MultipartFile
     * @param bookAdminRequestDto BookAdminRequestDto
     */
    void createBook(MultipartFile thumbnail, BookAdminRequestDto bookAdminRequestDto) throws IOException;

    /**
     * 도서 정보를 수정하는 메서드입니다.
     *
     * @param thumbnail MultipartFile
     * @param bookImages MultipartFile[]
     * @param bookAdminRequestDto BookAdminRequestDto
     */
    void updateBook(MultipartFile thumbnail, MultipartFile[] bookImages,
        BookAdminRequestDto bookAdminRequestDto);

    /**
     * editor에 업로드된 파일을 저장하는 메서드입니다.
     *
     * @param image MultipartFile
     * @return BookFileReadResponseDto
     */
    BookFileReadResponseDto uploadFile(MultipartFile image) throws IOException;

    Resource loadFile(String fileName);
}
