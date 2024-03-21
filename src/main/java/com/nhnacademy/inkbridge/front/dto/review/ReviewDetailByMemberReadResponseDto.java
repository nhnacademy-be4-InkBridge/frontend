package com.nhnacademy.inkbridge.front.dto.review;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  class: ReviewReadResponseDto.
 *
 *  @author minm063
 *  @version 2024/03/19
 */
@Getter
@NoArgsConstructor
public class ReviewDetailByMemberReadResponseDto {

    private Long reviewId;
    private String reviewTitle;
    private String reviewContent;
    private LocalDateTime registeredAt;
    private Integer score;
}
