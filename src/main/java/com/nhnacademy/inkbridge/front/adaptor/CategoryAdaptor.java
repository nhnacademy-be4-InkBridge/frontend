package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.category.CategoryCreateRequestDto;

/**
 * class: CategoryAdaptor.
 *
 * @author choijaehun
 * @version 2024/02/24
 */
public interface CategoryAdaptor {

    void createCategory(CategoryCreateRequestDto requestDto);
}
