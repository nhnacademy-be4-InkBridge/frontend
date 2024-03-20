package com.nhnacademy.inkbridge.front.dto.author;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

/**
 * class: AuthorInfoReadResponseDto.
 *
 * @author minm063
 * @version 2024/03/15
 */
@Getter
public class AuthorCreateUpdateRequestDto {

    @NotBlank
    @Size(max = 20)
    private final String authorName;

    @NotBlank
    @Size(max = 100)
    private final String authorIntroduce;

    public AuthorCreateUpdateRequestDto(String authorName, String authorIntroduce) {
        this.authorName = authorName;
        this.authorIntroduce = authorIntroduce;
    }
}
