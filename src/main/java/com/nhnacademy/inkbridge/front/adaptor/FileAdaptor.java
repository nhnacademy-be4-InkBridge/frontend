package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.book.BookFileReadResponseDto;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: FileAdaptor.
 *
 * @author minm063
 * @version 2024/02/29
 */
public interface FileAdaptor {

    /**
     * editor에 업로드된 파일을 서버로 전송하는 메서드입니다.
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
