package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.book.BookFileReadResponseDto;
import com.nhnacademy.inkbridge.front.service.FileService;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@Slf4j
@RestController
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 파일을 저장하는 메서드입니다.
     *
     * @param image MultipartFile
     * @return BookFileReadResponseDto
     */
    @PostMapping("/image-upload")
    public ResponseEntity<BookFileReadResponseDto> saveFile(@RequestParam MultipartFile image) {
        try {
            return new ResponseEntity<>(fileService.uploadFile(image), HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 파일을 byte[]로 조회하는 메서드입니다.
     *
     * @param fileName String
     * @return byte[]
     */
    @GetMapping("/image-load/{fileName}")
    public byte[] loadFile(@PathVariable String fileName) {
        return fileService.loadFile(fileName);
    }

}
