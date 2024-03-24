package com.nhnacademy.inkbridge.front.dto.category;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: CategoryNameReadResponseDto.
 *
 * @author minm063
 * @version 2024/03/19
 */
@Getter
@NoArgsConstructor
public class CategoryNameReadResponseDto {

    private String categoryName;
    private String parentCategoryName;
}
