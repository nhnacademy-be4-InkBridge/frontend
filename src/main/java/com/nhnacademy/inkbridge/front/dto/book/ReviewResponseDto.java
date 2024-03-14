package com.nhnacademy.inkbridge.front.dto.book;

import java.time.LocalDate;
import lombok.Getter;

/**
 * class: ReviewResponseDto.
 *
 * @author minm063
 * @version 2024/03/02
 */
@Getter
public class ReviewResponseDto {
    private Long reviewId;
    private String reviewTitle;
    private String reviewContent;
    private LocalDate registeredAt;
    private Integer score;
}
