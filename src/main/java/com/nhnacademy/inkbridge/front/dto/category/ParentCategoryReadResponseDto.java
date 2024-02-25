package com.nhnacademy.inkbridge.front.dto.category;

/**
 * class: ParentCategoryReadResponseDto.
 *
 * @author choijaehun
 * @version 2024/02/25
 */

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParentCategoryReadResponseDto {

    private Long categoryId;
    private String categoryName;
    private List<ParentCategoryReadResponseDto> parentCategories;

//    public ParentCategoryReadResponseDto(Category category) {
//        this.categoryId = category.getCategoryId();
//        this.categoryName = category.getCategoryName();
//        this.parentCategories = category.getCategoryChildren().stream()
//            .map(ParentCategoryReadResponseDto::new)
//            .collect(Collectors.toList());
//    }
}