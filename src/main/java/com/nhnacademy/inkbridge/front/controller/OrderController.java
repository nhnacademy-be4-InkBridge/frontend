package com.nhnacademy.inkbridge.front.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.inkbridge.front.dto.order.OrderBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.order.OrderCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.pay.PayReadResponseDto;
import com.nhnacademy.inkbridge.front.service.AccumulationRatePolicyService;
import com.nhnacademy.inkbridge.front.service.DeliveryPolicyService;
import com.nhnacademy.inkbridge.front.service.OrderService;
import com.nhnacademy.inkbridge.front.service.WrappingService;
import com.nhnacademy.inkbridge.front.utils.CookieUtils;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
    private final ObjectMapper objectMapper;

    /**
     * 주문서 작성 페이지 호출 요청을 처리하는 메소드입니다.
     *
     * @param model Model
     * @return 주문서 작성 페이지, Cookie에 도서 정보가 존재하지 않는다면 메인페이지로 이동
     */
    @GetMapping
    public String orderView(Model model,
        @CookieValue(value = "info", required = false) String booksInfo,
        HttpServletResponse response) {

//        model.addAttribute("orderBooks",
//            List.of(
//                new OrderBookReadResponseDto(1L, null,  "블랙 쇼맨과 운명의 바퀴", 19800L, 17820L, 1 , true),
//                new OrderBookReadResponseDto(2L, null, "명탐견 오드리, 예감은 꼬리에서부터", 12000L, 10800L, 1, true),
//                new OrderBookReadResponseDto(3L, null, "새콤달콤 캐치! 티니핑 홈스쿨 세계 여러 나라: 아시아 편", 12000L,
//                    10800L, 1, true)));
//        도서 정보
//        상품 상세페이지, 장바구니에서 쿠키에 담겨서 넘어옴
//        값을 모델에 넣은 후 Cookie 삭제
//        쿠키에서 book info dto 받아 model에 넣고 cookie 삭제

        CookieUtils.deleteCookie(response, "info");

        List<OrderBookReadResponseDto> orderBooks = null;
        try {
            orderBooks = objectMapper.readValue(booksInfo,
                new TypeReference<>() {
                });
        } catch (JsonProcessingException ignore) {
        }

        if (Objects.isNull(orderBooks)) {
            return "redirect:/";
        }

        model.addAttribute("orderBooks", orderBooks);
        model.addAttribute("deliveryPolicy", deliveryPolicyService.getCurrentPolicy());
        model.addAttribute("accumulationRatePolicy",
            accumulationRatePolicyService.getCurrentPolicy());
        model.addAttribute("wrappingList", wrappingService.getWrappingList(true));

//      멤버 포인트 조회 메소드 호출
        return "order/orders";
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
//      여기서 주문정보를 DB에 저장하고 결제 페이지로 이동 (Return OrderId)
//      requestDto + principal (memberId or "anonymousUser")
//      RedirectAttributes paymentInfo 객체 add.

        String principal = (String) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();

        if (!"anonymousUser".equals(principal)) {
            requestDto.setMemberId(principal);
        }

//      PayReadResponseDto responseDto = orderService.createOrder(requestDto);
        String orderId = UUID.randomUUID().toString().replace("-", "");

//        requestDto.setOrderId(responseDto.getOrderId());
        requestDto.setOrderId(orderId);

        log.info("create order {}", requestDto);

//      redirectAttributes.addFlashAttribute("orderInfo", responseDto);
        redirectAttributes.addFlashAttribute("orderInfo",
            new PayReadResponseDto(orderId, requestDto.getOrderName(), requestDto.getPayAmount()));

        // 결제 페이지로 변경해야함.
        return "redirect:/pays";
    }

}
