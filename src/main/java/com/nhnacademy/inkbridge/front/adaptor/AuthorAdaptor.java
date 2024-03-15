package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.author.AuthorCreateUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.author.AuthorInfoReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.author.AuthorReadResponseDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: AuthorAdaptor.
 *
 * @author minm063
 * @version 2024/03/14
 */
public interface AuthorAdaptor {

    /**
     * 작가 아이디에 대한 작가 상세와 도서 정보를 조회하는 임시 메서드입니다.
     *
     * @param authorId Long
     * @param page     Long
     * @param size     Long
     * @return AuthorReadResponseDto
     */
    AuthorReadResponseDto getAuthor(Long authorId, Long page, Long size);

    /**
     * 관리자 페이지에서 전체 작가 목록을 조회하는 메서드입니다.
     *
     * @param page Long
     * @param size Long
     * @return AuthorInfoReadResponseDto
     */
    PageRequestDto<AuthorInfoReadResponseDto> getAuthors(Long page, Long size);

    /**
     * 작가를 생성하는 메서드입니다.
     *
     * @param authorFile                   MultipartFile
     * @param authorCreateUpdateRequestDto AuthorCreateUpdateRequestDto
     */
    void createAuthor(MultipartFile authorFile,
        AuthorCreateUpdateRequestDto authorCreateUpdateRequestDto);

    /**
     * 작가를 수정하는 메서드입니다.
     *
     * @param authorFile                   MultipartFile
     * @param authorCreateUpdateRequestDto AuthorCreateUpdateRequestDto
     * @param authorId                     Long
     */
    void updateAuthor(MultipartFile authorFile,
        AuthorCreateUpdateRequestDto authorCreateUpdateRequestDto, Long authorId);
}
