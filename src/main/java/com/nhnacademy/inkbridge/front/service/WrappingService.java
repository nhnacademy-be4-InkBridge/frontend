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

    /**
     * 포장지 리스트 가져오기.
     *
     * @param isActive 활성여부
     * @return 포장지 리스트
     */
    List<WrappingReadResponseDto> getWrappingList(boolean isActive);

    /**
     * 포장지 상세 정보가져오기.
     *
     * @param wrappingId 조회할 포장지 id
     * @return 포장지 데이터
     */
    WrappingReadResponseDto getWrapping(Long wrappingId);

    /**
     * 포장지 업데이트.
     *
     * @param wrappingId               업데이트할 포장지 id
     * @param wrappingCreateRequestDto 업데이트할 내용
     */
    void updateWrapping(Long wrappingId, WrappingCreateRequestDto wrappingCreateRequestDto);

    /**
     * 포장지 생성.
     *
     * @param wrappingCreateRequestDto 생성할 내용
     */
    void registerWrapping(WrappingCreateRequestDto wrappingCreateRequestDto);
}
