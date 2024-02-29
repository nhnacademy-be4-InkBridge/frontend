package com.nhnacademy.inkbridge.front.dto.book;

import com.nhnacademy.inkbridge.front.dto.bookstatus.BookStatusReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.category.ParentCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.tag.TagReadResponseDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * class: BookAdminReadResponse.
 *
 * @author minm063
 * @version 2024/02/29
 */
@Getter
public class BookAdminDetailReadResponseDto {
    BookAdminSelectedReadResponseDto adminSelectedReadResponseDto;
    List<ParentCategoryReadResponseDto> parentCategoryReadResponseDtoList;
    List<PublisherReadResponseDto> publisherReadResponseDtoList;
    List<AuthorReadResponseDto> authorReadResponseDtoList;
    List<BookStatusReadResponseDto> bookStatusReadResponseDtoList;
    List<TagReadResponseDto> tagReadResponseDtoList;

    // builder

    @Builder
    public BookAdminDetailReadResponseDto(BookAdminSelectedReadResponseDto adminSelectedReadResponseDto,
        List<ParentCategoryReadResponseDto> parentCategoryReadResponseDtoList,
        List<PublisherReadResponseDto> publisherReadResponseDtoList,
        List<AuthorReadResponseDto> authorReadResponseDtoList,
        List<BookStatusReadResponseDto> bookStatusReadResponseDtoList,
        List<TagReadResponseDto> tagReadResponseDtoList) {
        this.adminSelectedReadResponseDto = adminSelectedReadResponseDto;
        this.parentCategoryReadResponseDtoList = parentCategoryReadResponseDtoList;
        this.publisherReadResponseDtoList = publisherReadResponseDtoList;
        this.authorReadResponseDtoList = authorReadResponseDtoList;
        this.bookStatusReadResponseDtoList = bookStatusReadResponseDtoList;
        this.tagReadResponseDtoList = tagReadResponseDtoList;
    }
}
