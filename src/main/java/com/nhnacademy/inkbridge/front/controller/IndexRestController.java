package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.book.BooksReadResponseDto;
import com.nhnacademy.inkbridge.front.service.IndexService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * class: IndexRestController.
 *
 * @author minm063
 * @version 2024/03/03
 */
@RestController
@RequestMapping("/api/index")
public class IndexRestController {

    private final IndexService indexService;

    public IndexRestController(IndexService indexService) {
        this.indexService = indexService;
    }

    /**
     * 도서 목록을 조회하는 api입니다. 무한 스크롤링에 사용됩니다.
     *
     * @return BooksReadResponseDto
     */
    @GetMapping
    public BooksReadResponseDto indexList(@RequestParam(value = "page", defaultValue = "0") Long page) {
        return indexService.getBooks(page);
    }
}
