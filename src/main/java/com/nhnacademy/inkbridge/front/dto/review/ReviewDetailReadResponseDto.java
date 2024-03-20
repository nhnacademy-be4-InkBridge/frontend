package com.nhnacademy.inkbridge.front.dto.review;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: ReviewReadResponseDto.
 *
 * @author minm063
 * @version 2024/03/19
 */
@Getter
@NoArgsConstructor
public class ReviewDetailReadResponseDto {

    private String reviewTitle;
    private String reviewContent;
    private LocalDateTime registeredAt;
    private Integer score;
    private List<String> fileUrls;
}
