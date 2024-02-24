package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.category.CategoryCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.category.CategoryReadResponseDto;
import java.util.List;

/**
 * class: CategoryAdaptor.
 *
 * @author choijaehun
 * @version 2024/02/24
 */
public interface CategoryAdaptor {

    void createCategory(CategoryCreateRequestDto requestDto);

    List<CategoryReadResponseDto> readCategories();
}
