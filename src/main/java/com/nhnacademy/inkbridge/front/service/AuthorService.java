package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.author.AuthorReadResponseDto;

/**
 * class: AuthorService.
 *
 * @author minm063
 * @version 2024/03/14
 */
public interface AuthorService {

    AuthorReadResponseDto getAuthor(Long authorId, Long page);
}
