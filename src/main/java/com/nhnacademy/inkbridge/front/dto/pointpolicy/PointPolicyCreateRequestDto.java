package com.nhnacademy.inkbridge.front.dto.pointpolicy;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * class: PointPolicyCreateRequestDto.
 *
 * @author jangjaehun
 * @version 2024/02/22
 */

@Getter
@RequiredArgsConstructor
public class PointPolicyCreateRequestDto {

    @NotNull(message = "적립 포인트는 필수 입력 항목입니다.")
    @Min(value = 0, message = "적립 포인트는 음수일 수 없습니다.")
    private final Long accumulatePoint;

    @NotNull(message = "적립 포인트 아이디는 필수 입력 항목입니다.")
    private final Integer pointPolicyTypeId;
}