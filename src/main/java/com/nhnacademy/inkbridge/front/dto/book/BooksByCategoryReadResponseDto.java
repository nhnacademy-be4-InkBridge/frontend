package com.nhnacademy.inkbridge.front.dto.book;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.author.AuthorPaginationReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.category.CategoryNameReadResponseDto;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: BooksReadResponseDto.
 *
 * @author minm063
 * @version 2024/03/13
 */
@Getter
@NoArgsConstructor
public class BooksByCategoryReadResponseDto {

    private PageRequestDto<BooksPaginationReadResponseDto> booksPaginationReadResponseDtos;
    private List<AuthorPaginationReadResponseDto> authorPaginationReadResponseDto;
    private CategoryNameReadResponseDto categoryNameReadResponseDto;
}
