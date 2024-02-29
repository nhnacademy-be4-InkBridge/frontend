package com.nhnacademy.inkbridge.front.dto.book;

import com.nhnacademy.inkbridge.front.dto.bookstatus.BookStatusReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.category.ParentCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.tag.TagReadResponseDto;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: BookAdminReadResponse.
 *
 * @author minm063
 * @version 2024/02/22
 */
@Getter
@NoArgsConstructor
public class BookAdminReadResponseDto {

    private List<ParentCategoryReadResponseDto> parentCategoryReadResponseDtoList;
    private List<PublisherReadResponseDto> publisherReadResponseDtoList;
    private List<AuthorReadResponseDto> authorReadResponseDtoList;
    private List<BookStatusReadResponseDto> bookStatusReadResponseDtoList;
    private List<TagReadResponseDto> tagReadResponseDtoList;

    // builder

    @Builder
    public BookAdminReadResponseDto(
        List<ParentCategoryReadResponseDto> parentCategoryReadResponseDtoList,
        List<PublisherReadResponseDto> publisherReadResponseDtoList,
        List<AuthorReadResponseDto> authorReadResponseDtoList,
        List<BookStatusReadResponseDto> bookStatusReadResponseDtoList,
        List<TagReadResponseDto> tagReadResponseDtoList) {
        this.parentCategoryReadResponseDtoList = parentCategoryReadResponseDtoList;
        this.publisherReadResponseDtoList = publisherReadResponseDtoList;
        this.authorReadResponseDtoList = authorReadResponseDtoList;
        this.bookStatusReadResponseDtoList = bookStatusReadResponseDtoList;
        this.tagReadResponseDtoList = tagReadResponseDtoList;
    }
}
