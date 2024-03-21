package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.MemberGradeAdaptor;
import com.nhnacademy.inkbridge.front.dto.member.MemberGradeReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.member.MemberGradeUpdateRequestDto;
import com.nhnacademy.inkbridge.front.service.MemberGradeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * class: MemberGradeServiceImpl.
 *
 * @author jeongbyeonghun
 * @version 3/21/24
 */
@Service
@RequiredArgsConstructor
public class MemberGradeServiceImpl implements MemberGradeService {

    private final MemberGradeAdaptor memberGradeAdaptor;


    @Override
    public List<MemberGradeReadResponseDto> getGrades() {
        return memberGradeAdaptor.getGrades();
    }

    @Override
    public void updateGrade(Long gradeId, MemberGradeUpdateRequestDto memberGradeUpdateRequestDto) {
        memberGradeAdaptor.updateGrade(gradeId, memberGradeUpdateRequestDto);
    }
}
