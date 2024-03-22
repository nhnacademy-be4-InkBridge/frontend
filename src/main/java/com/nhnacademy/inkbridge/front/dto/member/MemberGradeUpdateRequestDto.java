package com.nhnacademy.inkbridge.front.dto.member;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

/**
 * class: MemberGradeUpdateRequestDto.
 *
 * @author jeongbyeonghun
 * @version 3/21/24
 */

@Getter
public class MemberGradeUpdateRequestDto {

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private BigDecimal pointRate;

    @NotNull
    @Min(0)
    private Long standardAmount;

    @Builder
    public MemberGradeUpdateRequestDto(BigDecimal pointRate, Long standardAmount) {
        this.pointRate = pointRate;
        this.standardAmount = standardAmount;
    }
}
