package com.nhnacademy.inkbridge.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * class: ErrorController.
 *
 * @author minm063
 * @version 2024/03/14
 */
@Controller
public class ErrorController {

    /**
     * 에러 페이지를 조회하는 메서드입니다.
     *
     * @return html
     */
    @GetMapping("/error")
    public String goError() {
        return "error";
    }
}
