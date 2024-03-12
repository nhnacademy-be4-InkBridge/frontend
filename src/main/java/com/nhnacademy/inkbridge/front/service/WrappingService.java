package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.wrapping.WrappingReadResponseDto;
import java.util.List;

/**
 * class: WrappingService.
 *
 * @author jangjaehun
 * @version 2024/03/05
 */
public interface WrappingService {

    List<WrappingReadResponseDto> getWrappingList(boolean isActive);
}
