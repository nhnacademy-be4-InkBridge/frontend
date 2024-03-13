package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.category.ParentCategoryReadResponseDto;
import java.util.List;

/**
 * class: IndexAdaptor.
 *
 * @author minm063
 * @version 2024/02/26
 */
public interface IndexAdaptor {

    PageRequestDto<BooksReadResponseDto> getBooks(Long page);

    BookReadResponseDto getBook(Long bookId);

    /**
     * 전체 카테고리 목록을 호출하는 메소드입니다.
     *
     * @return List - CategoryReadResponseDto
     */
    List<ParentCategoryReadResponseDto> readCategories();
}
