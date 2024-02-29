package com.nhnacademy.inkbridge.front.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class: TestController.
 *
 * @author ijeongbeom
 * @version 2024/02/14
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
