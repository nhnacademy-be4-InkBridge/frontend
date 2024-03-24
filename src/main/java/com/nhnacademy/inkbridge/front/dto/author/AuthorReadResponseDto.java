package com.nhnacademy.inkbridge.front.dto.author;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: AuthorReadResponseDto.
 *
 * @author minm063
 * @version 2024/02/29
 */
@Getter
@NoArgsConstructor
public class AuthorReadResponseDto {

    private Long authorId;
    private String authorName;
}
