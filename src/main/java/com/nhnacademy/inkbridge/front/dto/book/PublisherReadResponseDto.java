package com.nhnacademy.inkbridge.front.dto.book;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: PublisherReadResponseDto.
 *
 * @author minm063
 * @version 2024/02/29
 */
@Getter
@NoArgsConstructor
public class PublisherReadResponseDto {

    private Long publisherId;
    private String publisherName;
}
