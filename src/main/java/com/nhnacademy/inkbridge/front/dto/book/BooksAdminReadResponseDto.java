package com.nhnacademy.inkbridge.front.dto.book;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.author.AuthorPaginationReadResponseDto;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: BooksReadResponse.
 *
 * @author minm063
 * @version 2024/02/22
 */
@Getter
@NoArgsConstructor
public class BooksAdminReadResponseDto {

    private PageRequestDto<BooksAdminPaginationReadResponseDto> booksAdminPaginationReadResponseDtos;
    private List<AuthorPaginationReadResponseDto> authorPaginationReadResponseDtos;
}
