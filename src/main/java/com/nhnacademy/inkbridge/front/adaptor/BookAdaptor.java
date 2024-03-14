package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.book.BookAdminCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminDetailReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.cart.CartBookReadResponseDto;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: BookAdaptor.
 *
 * @author minm063
 * @version 2024/02/22
 */
public interface BookAdaptor {

    /**
     * 메인 페이지에서 도서 전체 목록을 조회하는 메서드입니다.
     *
     * @param page Long
     * @return Page BooksReadResponseDto
     */
    BooksReadResponseDto getBooks(Long page);

    /**
     * 카테고리 아이디에 따라 도서 목록을 조회하는 메서드입니다.
     *
     * @param page Long
     * @param categoryId Long
     * @return BooksReadResponseDto
     */
    BooksReadResponseDto getBooksByCategory(Long page, Long categoryId);

    /**
     * 도서 상세 페이지에서 도서 상세 정보를 조회하는 메서드입니다.
     *
     * @param bookId Long
     * @return BookReadResponseDto
     */
    BookReadResponseDto getBook(Long bookId);

    /**
     * 장바구니의 도서 아이디로 도서 상세 정보를 조회하는 메서드입니다.
     *
     * @param bookIdList Set
     * @return CartBookReadResponseDto
     */
    List<CartBookReadResponseDto> getBook(Set<String> bookIdList);

    /**
     * 관리자 페이지에서 도서 전체 목록을 조회하는 메서드입니다.
     *
     * @return List - BooksAdminReadResponseDto
     */
    BooksAdminReadResponseDto getBooksAdmin(Integer page, Integer size);

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
