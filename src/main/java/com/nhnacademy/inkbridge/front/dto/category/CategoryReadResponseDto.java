package com.nhnacademy.inkbridge.front.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * class: CategoryReadResponseDto.
 *
 * @author choijaehun
 * @version 2024/02/24
 */
@Getter
@AllArgsConstructor
public class CategoryReadResponseDto {

    private Long categoryId;
    private String categoryName;
}
