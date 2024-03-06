package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.order.OrderBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.wrapping.WrappingReadResponseDto;
import com.nhnacademy.inkbridge.front.service.AccumulationRatePolicyService;
import com.nhnacademy.inkbridge.front.service.DeliveryPolicyService;
import com.nhnacademy.inkbridge.front.service.OrderService;
import com.nhnacademy.inkbridge.front.service.WrappingService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * class: OrderController.
 *
 * @author jangjaehun
 * @version 2024/02/26
 */
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final AccumulationRatePolicyService accumulationRatePolicyService;
    private final DeliveryPolicyService deliveryPolicyService;
    private final WrappingService wrappingService;

    /**
     * 주문서 작성 페이지 호출 요청을 처리하는 메소드입니다.
     *
     * @param model Model
     * @return 주문서 작성 페이지, Cookie에 도서 정보가 존재하지 않는다면 메인페이지로 이동
     */
    @GetMapping
    public String orderView(Model model) {
        // 도서 정보
        // 상품 상세페이지, 장바구니에서 쿠키에 담겨서 넘어옴
        // 값을 모델에 넣은 후 Cookie 삭제
        model.addAttribute("orderBooks",
            List.of(
                new OrderBookReadResponseDto(1L, "블랙 쇼맨과 운명의 바퀴", true, 19800L, 17820L, 1),
                new OrderBookReadResponseDto(2L, "명탐견 오드리, 예감은 꼬리에서부터", true, 12000L, 10800L, 2),
                new OrderBookReadResponseDto(3L, "새콤달콤 캐치! 티니핑 홈스쿨 세계 여러 나라: 아시아 편", true, 12000L,
                    10800L, 3)));

        model.addAttribute("deliveryPolicy", deliveryPolicyService.getCurrentPolicy());
        model.addAttribute("accumulationRatePolicy",
            accumulationRatePolicyService.getCurrentPolicy());
        List<WrappingReadResponseDto> wrappingList = wrappingService.getWrappingList();
        log.info("{}", wrappingList);
        model.addAttribute("wrappingList", wrappingList);

        // 멤버 포인트 조회 메소드 호출

        return "member/orders";
    }

    /**
     * 주문 요청을 처리하고 결제 페이지로 이동하는 메소드입니다.
     *
     * @param requestDto 주문 정보
     * @return 결제 페이지
     */
    @PostMapping
    public String createOrder(@ModelAttribute OrderCreateRequestDto requestDto,
        RedirectAttributes redirectAttributes) {
        log.info("create order {}", requestDto);
        // 여기서 주문정보를 저장하고 결제 페이지로 이동 (Return OrderId)
        // requestDto + principal (memberId or "anonymousUser")
        // RedirectAttributes paymentInfo 객체 add.

        String principal = (String) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();

        if (!"anonymousUser".equals(principal)) {
            requestDto.setMemberId(principal);
        }

        String orderId = orderService.createOrder(requestDto);
        redirectAttributes.addFlashAttribute("orderId", orderId);
        // redirectAttributes.addFlashAttribute("paymentInfo", 결제 정보);

        // 결제 페이지로 변경해야함.
        return "redirect:/orders";
    }

}
