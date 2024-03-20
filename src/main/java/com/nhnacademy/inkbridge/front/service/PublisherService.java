package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.publisher.PublisherCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.publisher.PublisherReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.publisher.PublisherUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * class: PublisherService.
 *
 * @author choijaehun
 * @version 2024/03/20
 */
public interface PublisherService {

    /**
     * 출판사 생성 메소드
     * @param request 출판사 이름이 들어있는 Dto
     */

    void createPublisher(PublisherCreateRequestDto request);

    PageRequestDto<PublisherReadResponseDto> readPublishers(Pageable pageable);

    void updatePublisher(Long publisherId, PublisherUpdateRequestDto request);
}
