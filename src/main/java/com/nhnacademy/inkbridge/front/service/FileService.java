package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.book.BookFileReadResponseDto;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: FileService.
 *
 * @author minm063
 * @version 2024/02/29
 */
public interface FileService {

    /**
     * editor에 업로드된 파일을 저장하는 메서드입니다.
     *
     * @param image MultipartFile
     * @return BookFileReadResponseDto
     */
    BookFileReadResponseDto uploadFile(MultipartFile image) throws IOException;

    /**
     * fileName으로 해당 파일을 조회하는 메서드입니다.
     *
     * @param fileName String
     * @return byte[]
     */
    byte[] loadFile(String fileName);
}
