package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.order.OrderBookReadResponseDto;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class: OrderController.
 *
 * @author jangjaehun
 * @version 2024/02/26
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    /**
     * 주문서 작성 페이지 호출 요청을 처리하는 메소드입니다.
     *
     * @return 주문서 작성 페이지
     */
    @GetMapping
    public String orderView(Model model) {
        List<OrderBookReadResponseDto> orderBooks = List.of(
            new OrderBookReadResponseDto(1L, "기호1번 장재훈", 20000L, 17000L, 5, false),
            new OrderBookReadResponseDto(2L, "기호2번 이민서", 30000L, 28000L, 3, false),
            new OrderBookReadResponseDto(3L, "기호3번 김준현", 25000L, 24000L, 2, false),
            new OrderBookReadResponseDto(4L, "기호4번 정승조", 30000L, 27000L, 1, false)
        );

        model.addAttribute("orderBooks", orderBooks);
        return "member/orders";
    }

}
