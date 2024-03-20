package com.nhnacademy.inkbridge.front.dto.book;

import com.nhnacademy.inkbridge.front.dto.review.ReviewAverageReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewDetailReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewReadResponseDto;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  class: BookReadResponseDto.
 *
 *  @author minm063
 *  @version 2024/03/19
 */
@Getter
@NoArgsConstructor
public class BookReadResponseDto {

    private BookDetailReadResponseDto bookDetailReadResponseDto;
    private ReviewAverageReadResponseDto reviewAverageReadResponseDto;
}
