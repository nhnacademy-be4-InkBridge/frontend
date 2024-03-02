package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.BookCategoryAdaptor;
import com.nhnacademy.inkbridge.front.dto.bookCategory.BookCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.service.BookCategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * class: BookCategoryServiceImpl.
 *
 * @author choijaehun
 * @version 2024/02/28
 */
@Service
@RequiredArgsConstructor
public class BookCategoryServiceImpl implements BookCategoryService {

    private final BookCategoryAdaptor bookCategoryAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BookCategoryReadResponseDto> readBookCategories(Long bookId) {
        return bookCategoryAdaptor.readBookCategories(bookId);
    }
}
