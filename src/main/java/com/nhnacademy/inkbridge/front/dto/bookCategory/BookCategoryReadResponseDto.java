package com.nhnacademy.inkbridge.front.dto.bookCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: BookCategoryReadResponseDto.
 *
 * @author choijaehun
 * @version 2024/02/28
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class BookCategoryReadResponseDto {

    private Long categoryId;
    private Long bookId;
}
