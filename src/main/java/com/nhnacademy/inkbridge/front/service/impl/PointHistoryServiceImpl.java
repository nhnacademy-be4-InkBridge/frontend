package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.PointHistoryAdaptor;
import com.nhnacademy.inkbridge.front.dto.point.PointHistoryReadResponseDto;
import com.nhnacademy.inkbridge.front.service.PointHistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * class: PointHistoryServiceImpl.
 *
 * @author jeongbyeonghun
 * @version 3/22/24
 */
@Service
@RequiredArgsConstructor
public class PointHistoryServiceImpl implements PointHistoryService {
    private final PointHistoryAdaptor pointHistoryAdaptor;
    @Override
    public List<PointHistoryReadResponseDto> getPointHistory() {
        return pointHistoryAdaptor.getPointHistoryList();
    }
}
