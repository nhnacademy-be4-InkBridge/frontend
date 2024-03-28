package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.point.PointHistoryReadResponseDto;
import java.util.List;

/**
 * class: PointHistoryAdaptor.
 *
 * @author jeongbyeonghun
 * @version 3/22/24
 */
public interface PointHistoryAdaptor {

    List<PointHistoryReadResponseDto> getPointHistoryList();
}
