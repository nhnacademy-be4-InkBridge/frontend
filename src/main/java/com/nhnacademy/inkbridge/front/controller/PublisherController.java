package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.publisher.PublisherCreateRequestDto;
import com.nhnacademy.inkbridge.front.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class: PublisherController.
 *
 * @author choijaehun
 * @version 2024/03/20
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    public String readPublisher(@ModelAttribute PublisherCreateRequestDto request) {
        return "admin/publishers";
    }


}
