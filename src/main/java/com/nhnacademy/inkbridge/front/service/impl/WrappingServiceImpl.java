package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.WrappingAdaptor;
import com.nhnacademy.inkbridge.front.dto.wrapping.WrappingCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.wrapping.WrappingReadResponseDto;
import com.nhnacademy.inkbridge.front.service.WrappingService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * class: WrappingServiceImpl.
 *
 * @author jangjaehun
 * @version 2024/03/05
 */
@Service
@RequiredArgsConstructor
public class WrappingServiceImpl implements WrappingService {

    private final WrappingAdaptor wrappingAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WrappingReadResponseDto> getWrappingList(boolean isActive) {
        return wrappingAdaptor.getWrappingList(isActive);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WrappingReadResponseDto getWrapping(Long wrappingId) {
        return wrappingAdaptor.getWrapping(wrappingId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateWrapping(Long wrappingId, WrappingCreateRequestDto wrappingCreateRequestDto) {
        wrappingAdaptor.updateWrapping(wrappingId, wrappingCreateRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerWrapping(WrappingCreateRequestDto wrappingCreateRequestDto) {
        wrappingAdaptor.registerWrapping(wrappingCreateRequestDto);

    }
}
