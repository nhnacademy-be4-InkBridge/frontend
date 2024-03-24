package com.nhnacademy.inkbridge.front.controller;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.getMemberId;

import com.nhnacademy.inkbridge.front.dto.member.request.MemberUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.response.MemberInfoResponseDto;
import com.nhnacademy.inkbridge.front.service.CouponService;
import com.nhnacademy.inkbridge.front.service.MemberService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * class: MyPageController.
 *
 * @author JBum
 * @version 2024/03/12
 */
@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
    private final CouponService couponService;
    private final MemberService memberService;

    @GetMapping
    public String myPage(Model model) {
        MemberInfoResponseDto member = memberService.getMemberInfo();
        model.addAttribute("member", member);

        return "mypage/main";
    }

    @GetMapping("/coupons")
    public String myIssuedCouponPage(
            @RequestParam(name = "coupon-status-id", defaultValue = "1") Integer couponStatusId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size, Model model) {
        String memberId = (String) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        model.addAttribute("couponStatusId", couponStatusId);
        model.addAttribute("page",
                couponService.getIssuedCoupon(memberId, couponStatusId, page, size));
        return "coupon/my_coupon_list";
    }

    @GetMapping("/members")
    public String memberUpdate(Model model) {
        MemberInfoResponseDto member = memberService.getMemberInfo();
        model.addAttribute("member", member);

        return "mypage/members";
    }

    @PostMapping("/members")
    public String memberUpdateRequest(@Valid MemberUpdateRequestDto memberUpdateRequestDto) {
        Long memberId = getMemberId();

        memberService.updateMember(memberUpdateRequestDto, memberId);

        return "redirect:/mypage";
    }

    @GetMapping("/password")
    public String memberUpdatePasswordRequest() {

        return "mypage/password";
    }

    @GetMapping("/delete")
    public String memberExitRequest() {
        return "mypage/delete";
    }
    @PostMapping("/delete")
    public String memberDeleteRequest(HttpServletResponse response) {
        Long memberId = getMemberId();
        memberService.deleteMember(memberId);
        return "redirect:/logout";
    }
}
