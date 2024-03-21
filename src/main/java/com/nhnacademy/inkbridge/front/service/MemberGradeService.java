package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.member.MemberGradeReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.member.MemberGradeUpdateRequestDto;
import java.util.List;

/**
 * class: MemberGradeService.
 *
 * @author jeongbyeonghun
 * @version 3/21/24
 */
public interface MemberGradeService {
    List<MemberGradeReadResponseDto> getGrades();

    void updateGrade(Long gradeId, MemberGradeUpdateRequestDto memberGradeUpdateRequestDto);
}
