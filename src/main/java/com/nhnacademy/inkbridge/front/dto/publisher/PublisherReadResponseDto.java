package com.nhnacademy.inkbridge.front.dto.publisher;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: PublisherReadResponseDto.
 *
 * @author choijaehun
 * @version 2024/03/20
 */
@Getter
@NoArgsConstructor
public class PublisherReadResponseDto {
    private Long publisherId;
    private String publisherName;
}
