package com.nhnacademy.inkbridge.front.dto.review;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  class: ReviewAverageReadResponseDto.
 *
 *  @author minm063
 *  @version 2024/03/19
 */
@Getter
@NoArgsConstructor
public class ReviewAverageReadResponseDto {

    private Double avg;
    private Long count;
}
