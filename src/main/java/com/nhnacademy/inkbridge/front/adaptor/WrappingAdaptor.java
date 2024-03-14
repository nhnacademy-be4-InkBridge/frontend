package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.wrapping.WrappingReadResponseDto;
import java.util.List;

/**
 * class: WrappingAdaptor.
 *
 * @author jangjaehun
 * @version 2024/03/05
 */
public interface WrappingAdaptor {

    /**
     * 전체 포장지를 조회합니다.
     *
     * @return 전체 포장지 목록
     */
    List<WrappingReadResponseDto> getWrappingList();
}
