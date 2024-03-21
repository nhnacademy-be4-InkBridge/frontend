package com.nhnacademy.inkbridge.front.dto.member;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: MemberGradeReadResponseDto.
 *
 * @author jeongbyeonghun
 * @version 3/21/24
 */
@NoArgsConstructor
@Getter
public class MemberGradeReadResponseDto {
    private Integer gradeId;
    private String grade;
    private BigDecimal pointRate;
    private Long standardAmount;

    public MemberGradeReadResponseDto(Integer gradeId, String grade, BigDecimal pointRate,
        Long standardAmount) {
        this.gradeId = gradeId;
        this.grade = grade;
        this.pointRate = pointRate;
        this.standardAmount = standardAmount;
    }
}
