package com.nhnacademy.inkbridge.front.dto.book;

import com.nhnacademy.inkbridge.front.dto.author.AuthorReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.bookstatus.BookStatusReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.category.ParentCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.tag.TagReadResponseDto;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: BookAdminReadResponse.
 *
 * @author minm063
 * @version 2024/02/29
 */
@Getter
@NoArgsConstructor
public class BookAdminDetailReadResponseDto {

    private BookAdminSelectedReadResponseDto adminSelectedReadResponseDto;
    private List<ParentCategoryReadResponseDto> parentCategoryReadResponseDtoList;
    private List<PublisherReadResponseDto> publisherReadResponseDtoList;
    private List<AuthorReadResponseDto> authorReadResponseDtoList;
    private List<BookStatusReadResponseDto> bookStatusReadResponseDtoList;
    private List<TagReadResponseDto> tagReadResponseDtoList;
}
