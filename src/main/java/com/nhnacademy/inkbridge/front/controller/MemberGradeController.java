package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.member.MemberGradeUpdateRequestDto;
import com.nhnacademy.inkbridge.front.exception.MemberGradeException;
import com.nhnacademy.inkbridge.front.service.MemberGradeService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * class: MemberGradeController.
 *
 * @author jeongbyeonghun
 * @version 3/21/24
 */
@Controller
@RequestMapping("/admin/grades")
@RequiredArgsConstructor
public class MemberGradeController {

    private final MemberGradeService memberGradeService;


    /**
     * 모든 회원 등급 정보를 조회합니다.
     *
     * @return 조회된 회원 등급 정보 리스트
     */
    @GetMapping
    public String getGradeList(Model model) {
        model.addAttribute("grades", memberGradeService.getGrades());

        return "admin/grade";
    }

    /**
     * 특정 회원 등급을 업데이트합니다.
     *
     * @param gradeId 회원 등급 ID
     * @param memberGradeUpdateRequestDto 업데이트할 회원 등급 정보
     */
    @PostMapping("/{gradeId}")
    public String updateGrade(@Valid @ModelAttribute MemberGradeUpdateRequestDto memberGradeUpdateRequestDto,
        @PathVariable("gradeId") Long gradeId, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new MemberGradeException("등급 수정 형식이 잘못 되었습니다.");
        }
        memberGradeService.updateGrade(gradeId, memberGradeUpdateRequestDto);
        return "redirect:/admin/grades";
    }

    @ExceptionHandler(value = MemberGradeException.class)
    public String gradeExceptionHandler(MemberGradeException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", e.getMessage());
        return "redirect:/admin/grades";
    }
}
