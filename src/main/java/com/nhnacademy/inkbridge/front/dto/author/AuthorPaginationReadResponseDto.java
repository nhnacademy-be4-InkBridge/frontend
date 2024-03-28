package com.nhnacademy.inkbridge.front.dto.author;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: AuthorPaginationReadResponseDto.
 *
 * @author minm063
 * @version 2024/03/13
 */
@Getter
@NoArgsConstructor
public class AuthorPaginationReadResponseDto {

    private List<String> authorName;
}
