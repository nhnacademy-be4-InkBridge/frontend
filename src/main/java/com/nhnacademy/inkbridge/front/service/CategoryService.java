package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.category.CategoryCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.category.CategoryUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.category.ParentCategoryReadResponseDto;
import java.util.List;

/**
 * class: CategoryService.
 *
 * @author choijaehun
 * @version 2024/02/24
 */
public interface CategoryService {

    void createCategory(CategoryCreateRequestDto requestDto);

    List<ParentCategoryReadResponseDto> readCategory();

    void updateCategory(Long categoryId, CategoryUpdateRequestDto requestDto);
}
