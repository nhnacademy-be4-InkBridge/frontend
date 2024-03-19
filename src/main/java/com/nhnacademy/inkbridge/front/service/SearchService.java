package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.search.BookSearchPageResponseDto;
import com.nhnacademy.inkbridge.front.dto.search.BookSearchResponseDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * class: SearchService.
 *
 * @author choijaehun
 * @version 2024/03/15
 */
public interface SearchService {

    /**
     *  검색어와 관련된 책을 찾는 메소드입니다.
     * @param text - 검색어
     * @param pageable - 페이지 기준
     * @return - 도서 집합
     */
    BookSearchPageResponseDto searchByText(String text, Pageable pageable);

    /**
     * 인기도서, 신상도서 등 정렬된 기준에 맞는 책을 찾는 메소드입니다.
     * @param field
     * @param pageable
     * @return - 도서 집합
     */
    BookSearchPageResponseDto searchByAll(String field, Pageable pageable);
}
