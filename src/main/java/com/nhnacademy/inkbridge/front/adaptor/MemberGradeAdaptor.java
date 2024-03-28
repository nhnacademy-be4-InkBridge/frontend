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

    /**
     * 모든 회원 등급 정보를 조회합니다.
     *
     * @return 조회된 회원 등급 정보를 담은 DTO 리스트
     */
    List<MemberGradeReadResponseDto> getGrades();

    /**
     * 특정 회원 등급 정보를 업데이트합니다.
     *
     * @param gradeId 업데이트할 회원 등급의 ID
     * @param memberGradeUpdateRequestDto 업데이트할 회원 등급 정보가 담긴 DTO
     */
    void updateGrade(Long gradeId, MemberGradeUpdateRequestDto memberGradeUpdateRequestDto);
}
