package com.nhnacademy.inkbridge.front.dto.review;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  class: ReviewOnBookReadResponseDto.
 *
 *  @author minm063
 *  @version 2024/03/21
 */
@Getter
@NoArgsConstructor
public class ReviewDetailOnBookReadResponseDto {

    private Long reviewId;
    private String reviewerEmail;
    private String reviewTitle;
    private String reviewContent;
    private LocalDate registeredAt;
    private Integer score;
}
