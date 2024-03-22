package com.nhnacademy.inkbridge.front.dto.review;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  class: ReviewReadResponseDto.
 *
 *  @author minm063
 *  @version 2024/03/20
 */
@Getter
@NoArgsConstructor
public class ReviewBookReadResponseDto {

    private PageRequestDto<ReviewDetailOnBookReadResponseDto> reviewDetailReadResponseDtos;
    private Map<Long, List<String>> reviewFiles;
}
