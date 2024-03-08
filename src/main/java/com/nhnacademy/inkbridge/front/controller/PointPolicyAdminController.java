package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.pointpolicy.PointPolicyAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.pointpolicy.PointPolicyCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.pointpolicytype.PointPolicyTypeCreateRequestDto;
import com.nhnacademy.inkbridge.front.service.PointPolicyService;
import com.nhnacademy.inkbridge.front.service.PointPolicyTypeService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * class: PointPolicyController.
 *
 * @author jangjaehun
 * @version 2024/02/22
 */
@Controller
@RequestMapping("/admin/point-policy")
@RequiredArgsConstructor
public class PointPolicyAdminController {

    private final PointPolicyService pointPolicyService;
    private final PointPolicyTypeService pointPolicyTypeService;


    /**
     * 포인트 정책 리스트 페이지를 호출하는 메소드입니다.
     *
     * @param pointPolicyTypeId Integer
     * @param model             Model
     * @return admin/point-policy-list
     */
    @GetMapping
    public String pointPolicyViewByTypeID(
        @RequestParam(value = "pointPolicyTypeId", required = false) Integer pointPolicyTypeId,
        Model model) {
        List<PointPolicyAdminReadResponseDto> pointPolicies =
            Objects.isNull(pointPolicyTypeId) ? pointPolicyService.getPointPolicies() :
                pointPolicyService.getPointPoliciesByTypeId(pointPolicyTypeId);

        model.addAttribute("pointPolicyCurrentList", pointPolicyService.getCurrentPointPolicies());
        model.addAttribute("pointPolicyTypeList", pointPolicyTypeService.getPointPolicyTypes());
        model.addAttribute("pointPolicyList", pointPolicies);
        model.addAttribute("pointPolicyTypeId", pointPolicyTypeId);
        return "admin/point-policy-list";
    }

    /**
     * 포인트 정책을 생성하는 메소드입니다.
     *
     * @param requestDto PointPolicyCreateRequestDto
     * @return redirect - 포인트 정책 리스트 페이지
     */
    @PostMapping()
    public String createPointPolicy(@ModelAttribute PointPolicyCreateRequestDto requestDto) {
        pointPolicyService.createPointPolicy(requestDto);

        return "redirect:/admin/point-policy";
    }

    /**
     * 포인트 정책 유형을 생성하는 메소드입니다.
     *
     * @param requestDto PointPolicyTypeCreateRequestDto
     * @return redirect - 포인트 정책 리스트 페이지
     */
    @PostMapping("/point-policy-type")
    public String registerPointPolicyType(
        @ModelAttribute PointPolicyTypeCreateRequestDto requestDto) {

        pointPolicyTypeService.createPointPolicyType(requestDto);

        return "redirect:/admin/point-policy";
    }
}
