package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.publisher.PublisherCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.publisher.PublisherReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.publisher.PublisherUpdateRequestDto;
import com.nhnacademy.inkbridge.front.service.PublisherService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public void createPublishers(@RequestBody PublisherCreateRequestDto request){
        publisherService.createPublisher(request);
    }

    @GetMapping
    public String readPublishers(Model model, @PageableDefault(size=10) Pageable pageable) {
        PageRequestDto<PublisherReadResponseDto> publishers=publisherService.readPublishers(pageable);
        model.addAttribute("publishers",publishers);
        return "admin/publishers";
    }

    @PutMapping("/{publishId}")
    public void updatePublisher(@PathVariable Long publishId, @RequestBody PublisherUpdateRequestDto request){
        publisherService.updatePublisher(publishId,request);
    }
}
