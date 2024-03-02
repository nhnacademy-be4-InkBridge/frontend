package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.FileAdaptor;
import com.nhnacademy.inkbridge.front.dto.book.BookFileReadResponseDto;
import com.nhnacademy.inkbridge.front.service.FileService;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: FileServiceImpl.
 *
 * @author minm063
 * @version 2024/02/29
 */
@Service
public class FileServiceImpl implements FileService {

    private final FileAdaptor fileAdaptor;

    public FileServiceImpl(FileAdaptor fileAdaptor) {
        this.fileAdaptor = fileAdaptor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BookFileReadResponseDto uploadFile(MultipartFile image) throws IOException {
        return fileAdaptor.uploadFile(image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] loadFile(String fileName) {
        return fileAdaptor.loadFile(fileName);
    }
}
