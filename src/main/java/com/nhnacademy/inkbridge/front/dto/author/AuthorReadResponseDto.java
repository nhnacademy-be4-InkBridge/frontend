package com.nhnacademy.inkbridge.front.dto.author;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksPaginationReadResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: AuthorReadResponseDto.
 *
 * @author minm063
 * @version 2024/03/14
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorReadResponseDto {

    private PageRequestDto<BooksPaginationReadResponseDto> booksPaginationReadResponseDtos;
    private AuthorInfoReadResponseDto authorInfoReadResponseDto;
}
