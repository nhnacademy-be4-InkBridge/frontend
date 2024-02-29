package com.nhnacademy.inkbridge.front.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: PublisherReadResponseDto.
 *
 * @author minm063
 * @version 2024/02/29
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherReadResponseDto {
    private Long publisherId;
    private String publisherName;
}
