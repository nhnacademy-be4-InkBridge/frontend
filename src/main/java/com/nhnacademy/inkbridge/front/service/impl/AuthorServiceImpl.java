package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.AuthorAdaptor;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.author.AuthorCreateUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.author.AuthorInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: AuthorServiceImpl.
 *
 * @author minm063
 * @version 2024/03/14
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorAdaptor authorAdaptor;

    public AuthorServiceImpl(AuthorAdaptor authorAdaptor) {
        this.authorAdaptor = authorAdaptor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthorInfoReadResponseDto getAuthor(Long authorId, Long page, Long size) {
        return authorAdaptor.getAuthor(authorId, page, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageRequestDto<AuthorInfoReadResponseDto> getAuthors(Long page, Long size) {
        return authorAdaptor.getAuthors(page, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createAuthor(MultipartFile authorFile,
        AuthorCreateUpdateRequestDto authorCreateUpdateRequestDto) {
        authorAdaptor.createAuthor(authorFile, authorCreateUpdateRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAuthor(MultipartFile authorFile,
        AuthorCreateUpdateRequestDto authorCreateUpdateRequestDto, Long authorId) {
        authorAdaptor.updateAuthor(authorFile, authorCreateUpdateRequestDto, authorId);
    }
}
