package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.publisher.PublisherCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.publisher.PublisherReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.publisher.PublisherUpdateRequestDto;
import org.springframework.data.domain.Pageable;

/**
 * class: PublisherAdaptor.
 *
 * @author choijaehun
 * @version 2024/03/20
 */
public interface PublisherAdaptor {

    /**
     * 출판사 생성하는 메소드.
     *
     * @param request 출판사 생성을 요청하는 Dto
     *
     */
    void createPublisher(PublisherCreateRequestDto request);

    /**
     * 출판사 리스트를 호출하는 메소드.
     *
     * @return 출판사 리스트
     *
     */
    PageRequestDto<PublisherReadResponseDto> readPublishers(Pageable pageable);

    /**
     * 출판사이름 수정 메소드.
     * @param request 수정할 이름이 들어있는 dto
     *
     */
    void updatePublisher(Long publisherId, PublisherUpdateRequestDto request);
}
