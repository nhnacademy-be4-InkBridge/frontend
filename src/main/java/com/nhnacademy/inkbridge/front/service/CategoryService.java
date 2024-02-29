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

    /**
     * 카테고리 생성하는 메소드입니다.
     *
     * @param requestDto CategoryCreateRequestDto
     */
    void createCategory(CategoryCreateRequestDto requestDto);

    /**
     * 카테고리 목록을 보여주는 메소드입니다.
     *
     * @return List<ParentCategoryReadResponseDto>
     */
    List<ParentCategoryReadResponseDto> readCategory();

    /**
     * 카테고리 수정하는 메소드입니다.
     *
     * @param categoryId Long
     * @param requestDto CategoryUpdateRequestDto
     */
    void updateCategory(Long categoryId, CategoryUpdateRequestDto requestDto);
}
