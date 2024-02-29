package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.bookCategory.BookCategoryReadResponseDto;
import java.util.List;

/**
 * class: BookCategoryAdaptor.
 *
 * @author choijaehun
 * @version 2024/02/28
 */
public interface BookCategoryAdaptor {

    /**
     * 첵이 속한 카테고리를 반환해주는 메소드입니다.
     *
     * @param bookId 책 번호
     * @return book과 관련된 category가 있는 BookCategoryReadResponseDto
     */
    List<BookCategoryReadResponseDto> readBookCategories(Long bookId);
}
