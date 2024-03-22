package com.nhnacademy.inkbridge.front.dto.book;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.author.AuthorPaginationReadResponseDto;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: BookReadResponseDto.
 *
 * @author minm063
 * @version 2024/02/26
 */
@Getter
@NoArgsConstructor
public class BooksReadResponseDto {

    private PageRequestDto<BooksPaginationReadResponseDto> booksPaginationReadResponseDtos;
    private List<AuthorPaginationReadResponseDto> authorPaginationReadResponseDto;
}
