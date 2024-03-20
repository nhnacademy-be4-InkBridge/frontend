package com.nhnacademy.inkbridge.front.dto.review;

import java.math.BigDecimal;
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

    private BigDecimal avg;
}
