package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.SearchAdaptor;
import com.nhnacademy.inkbridge.front.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * class: SearchServiceImpl.
 *
 * @author choijaehun
 * @version 2024/03/15
 */

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final SearchAdaptor searchAdaptor;

}
