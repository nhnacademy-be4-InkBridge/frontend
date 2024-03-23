package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.search.BookSearchResponseDto;
import org.springframework.data.domain.Pageable;

/**
 * class: SearchAdaptor.
 *
 * @author choijaehun
 * @version 2024/03/15
 */
public interface SearchAdaptor {

    /**
     * 검색어와 관련된 책을 찾는 메소드입니다.
     *
     * @param text     - 검색어
     * @param pageable - 페이지 기준
     * @return - 도서 집합
     */
    PageRequestDto<BookSearchResponseDto> searchByText(String text, Pageable pageable);

    /**
     * 인기도서, 신상도서 등 정렬된 기준에 맞는 책을 찾는 메소드입니다.
     *
     * @param field
     * @param pageable
     * @return - 도서 집합
     */
    PageRequestDto<BookSearchResponseDto> searchByAll(String field, Pageable pageable);

    /**
     * 카테고리에 속한 책을 찾는 메서드입니다.
     *
     * @param category 카테고리명
     * @param pageable 페이지 기준
     * @return - 도서 집합
     */
    PageRequestDto<BookSearchResponseDto> readByCategory(String category, Pageable pageable);
}
