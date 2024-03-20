package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.PublisherAdaptor;
import com.nhnacademy.inkbridge.front.dto.publisher.PublisherCreateRequestDto;
import com.nhnacademy.inkbridge.front.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * class: PublisherServiceImpl.
 *
 * @author choijaehun
 * @version 2024/03/20
 */

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {


    private final PublisherAdaptor publisherAdaptor;

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void createPublisher(PublisherCreateRequestDto request) {
        publisherAdaptor.createPublisher(request);
    }
}
