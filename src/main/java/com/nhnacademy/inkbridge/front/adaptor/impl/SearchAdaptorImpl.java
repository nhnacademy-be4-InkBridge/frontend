package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.SearchAdaptor;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * class: SearchAdaptorImpl.
 *
 * @author choijaehun
 * @version 2024/03/15
 */

@Component
@RequiredArgsConstructor
public class SearchAdaptorImpl implements SearchAdaptor {

    private final RestTemplate restTemplate;

    private final GatewayProperties gatewayProperties;
}
