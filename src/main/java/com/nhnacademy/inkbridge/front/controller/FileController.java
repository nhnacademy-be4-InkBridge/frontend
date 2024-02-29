package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.book.BookFileReadResponseDto;
import com.nhnacademy.inkbridge.front.service.FileService;
import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: FileController.
 *
 * @author minm063
 * @version 2024/02/26
 */
@RestController
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }


    @PostMapping("/image-upload")
    public BookFileReadResponseDto saveFile(@RequestParam MultipartFile image) {
        try {
            return fileService.uploadFile(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/image-load")
    public byte[] loadFile(@RequestParam(name = "filename") String fileName) {
        return fileService.loadFile(fileName);
    }

}
