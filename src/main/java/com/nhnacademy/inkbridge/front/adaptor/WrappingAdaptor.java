package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.wrapping.WrappingCreateRequestDto;
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
     * 데이터베이스에서 포장지 리스트 가져오기.
     *
     * @param isActive 활성 여부
     * @return 포장지 리스트
     */
    List<WrappingReadResponseDto> getWrappingList(boolean isActive);

    /**
     * 데이터베이스에서 포장지 상세정보 가져오기.
     *
     * @param wrappingId 가져올 포장지 id
     * @return 포장지 상세 정보
     */
    WrappingReadResponseDto getWrapping(Long wrappingId);

    /**
     * 데이터베이스에 포장지 업데이트 하기.
     *
     * @param wrappingId              업데이트할 포장지 id
     * @param wrappingReadResponseDto 업데이트할 내용
     */
    void updateWrapping(Long wrappingId, WrappingCreateRequestDto wrappingReadResponseDto);

    /**
     * 데이터베이스에 포장지 생성.
     *
     * @param wrappingCreateRequestDto 생성할 내용
     */
    void registerWrapping(WrappingCreateRequestDto wrappingCreateRequestDto);
}
