package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.service.PointHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class: PointHistoryController.
 *
 * @author jeongbyeonghun
 * @version 3/22/24
 */
@Controller
@RequestMapping("/mypage/point")
@RequiredArgsConstructor
public class PointHistoryController {

    private final PointHistoryService pointHistoryService;

    @GetMapping
    String getPointHistory(Model model) {
        model.addAttribute("pointHistoryList", pointHistoryService.getPointHistory());
        return "mypage/point_history";
    }
}
