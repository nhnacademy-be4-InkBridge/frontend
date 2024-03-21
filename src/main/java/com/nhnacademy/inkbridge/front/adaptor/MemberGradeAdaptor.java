package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.member.MemberGradeReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.member.MemberGradeUpdateRequestDto;
import java.util.List;

/**
 * class: MemberGradeAdaptor.
 *
 * @author jeongbyeonghun
 * @version 3/21/24
 */
public interface MemberGradeAdaptor {

    List<MemberGradeReadResponseDto> getGrades();

    void updateGrade(Long gradeId, MemberGradeUpdateRequestDto memberGradeUpdateRequestDto);
}
