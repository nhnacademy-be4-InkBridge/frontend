package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.wrapping.WrappingCreateRequestDto;
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

    WrappingReadResponseDto getWrapping(Long wrappingId);

    void updateWrapping(Long wrappingId, WrappingCreateRequestDto wrappingCreateRequestDto);

    void registerWrapping(WrappingCreateRequestDto wrappingCreateRequestDto);
}
