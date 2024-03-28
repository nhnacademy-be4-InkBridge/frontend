package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.point.PointHistoryReadResponseDto;
import java.util.List;

/**
 * class: PointHistoryService.
 *
 * @author jeongbyeonghun
 * @version 3/22/24
 */
public interface PointHistoryService {

    List<PointHistoryReadResponseDto> getPointHistory();
}
