package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.book.BooksReadResponseDto;
import com.nhnacademy.inkbridge.front.service.IndexService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping
    public List<BooksReadResponseDto> indexList() {
        return indexService.getBooks();
    }
}
