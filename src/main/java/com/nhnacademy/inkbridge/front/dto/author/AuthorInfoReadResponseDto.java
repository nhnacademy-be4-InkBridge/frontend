package com.nhnacademy.inkbridge.front.dto.author;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: AuthorInfoReadResponseDto.
 *
 * @author minm063
 * @version 2024/03/15
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorInfoReadResponseDto {

    private String authorName;
    private String authorIntroduce;
    private String fileUrl;

}
