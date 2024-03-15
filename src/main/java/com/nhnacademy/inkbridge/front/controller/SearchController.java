package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class: SearchController.
 *
 * @author choijaehun
 * @version 2024/03/15
 */

@Controller
//@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

//    private final SearchService searchService;

    @GetMapping
    public String view(){
        return "search/search";
    }
}

