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
    /**
     * 카테고리를 생성하는 메소드입니다.
     *
     * @param requestDto CategoryCreateRequestDto
     */
    void createCategory(CategoryCreateRequestDto requestDto);

    /**
     * 전체 카테고리 목록을 호출하는 메소드입니다.
     *
     * @return List - CategoryReadResponseDto
     */
    List<ParentCategoryReadResponseDto> readCategories();

    /**
     * 카테고리 수정하는 메소드입니다.
     *
     * @param categoryId Long
     * @param requestDto CategoryUpdateRequestDto
     */
    void updateCategory(Long categoryId, CategoryUpdateRequestDto requestDto);
}
