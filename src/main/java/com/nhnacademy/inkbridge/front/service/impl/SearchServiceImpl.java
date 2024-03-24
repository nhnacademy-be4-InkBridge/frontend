package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.SearchAdaptor;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.search.BookSearchResponseDto;
import com.nhnacademy.inkbridge.front.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * class: SearchServiceImpl.
 *
 * @author choijaehun
 * @version 2024/03/15
 */

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final SearchAdaptor searchAdaptor;


    /**
     * {@inheritDoc}
     */
    @Override
    public PageRequestDto<BookSearchResponseDto> searchByText(String text, Pageable pageable) {
        return searchAdaptor.searchByText(text, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageRequestDto<BookSearchResponseDto> searchByAll(String field, Pageable pageable) {
        return searchAdaptor.searchByAll(field, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageRequestDto<BookSearchResponseDto> readByCategory(String category,
        Pageable pageable) {
        return searchAdaptor.readByCategory(category, pageable);
    }
}
