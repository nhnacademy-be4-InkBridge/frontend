package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.AuthorAdaptor;
import com.nhnacademy.inkbridge.front.dto.author.AuthorReadResponseDto;
import com.nhnacademy.inkbridge.front.service.AuthorService;
import org.springframework.stereotype.Service;

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

    @Override
    public AuthorReadResponseDto getAuthor(Long authorId, Long page) {
        return authorAdaptor.getAuthor(authorId, page);
    }
}
