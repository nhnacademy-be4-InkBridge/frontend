package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.author.AuthorReadResponseDto;

/**
 * class: AuthorAdaptor.
 *
 * @author minm063
 * @version 2024/03/14
 */
public interface AuthorAdaptor {

    AuthorReadResponseDto getAuthor(Long authorId, Long page);
}
