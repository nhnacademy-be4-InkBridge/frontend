package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.category.CategoryCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.category.CategoryUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.category.ParentCategoryReadResponseDto;
import java.util.List;

/**
 * class: CategoryAdaptor.
 *
 * @author choijaehun
 * @version 2024/02/24
 */
public interface CategoryAdaptor {

    void createCategory(CategoryCreateRequestDto requestDto);

    List<ParentCategoryReadResponseDto> readCategories();

    void updateCategory(Long categoryId, CategoryUpdateRequestDto requestDto);
}
