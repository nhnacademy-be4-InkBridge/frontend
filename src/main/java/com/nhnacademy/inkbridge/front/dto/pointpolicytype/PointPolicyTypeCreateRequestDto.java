package com.nhnacademy.inkbridge.front.dto.pointpolicytype;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * class: PointPolicyCraeteRequestDto.
 *
 * @author jangjaehun
 * @version 2024/02/22
 */
@Getter
@RequiredArgsConstructor
public class PointPolicyTypeCreateRequestDto {

    @NotNull(message = "정책 이름은 필수 입력 항목입니다.")
    @Length(max = 20, message = "정책 이름의 허용 길이는 1~20자 입니다.")
    private final String policyType;

    @NotNull(message = "정책 적립금액은 필수 입력 항목입니다.")
    @Min(value = 0, message = "적립금액은 0보다 작을 수 없습니다.")
    private final Long accumulatePoint;

}
