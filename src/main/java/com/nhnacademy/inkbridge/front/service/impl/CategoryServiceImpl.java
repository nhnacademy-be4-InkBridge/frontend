package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.CategoryAdaptor;
import com.nhnacademy.inkbridge.front.dto.category.CategoryCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.category.CategoryUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.category.ParentCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * class: CategoryServiceImpl.
 *
 * @author choijaehun
 * @version 2024/02/24
 */

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryAdaptor categoryAdaptor;

    /**
     * 카테고리 생성하는 메소드입니다.
     *
     * @param requestDto CategoryCreateRequestDto
     */
    @Override
    public void createCategory(CategoryCreateRequestDto requestDto) {
        categoryAdaptor.createCategory(requestDto);
    }


    /**
     * 카테고리 목록을 보여주는 메소드입니다.
     *
     * @return List<ParentCategoryReadResponseDto>
     */
    @Override
    public List<ParentCategoryReadResponseDto> readCategory() {
        return categoryAdaptor.readCategories();
    }


    /**
     * 카테고리 수정하는 메소드입니다.
     *
     * @param categoryId Long
     * @param requestDto CategoryUpdateRequestDto
     */
    @Override
    public void updateCategory(Long categoryId, CategoryUpdateRequestDto requestDto) {
        categoryAdaptor.updateCategory(categoryId, requestDto);
    }
}
