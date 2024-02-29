package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.PointPolicyTypeAdaptor;
import com.nhnacademy.inkbridge.front.dto.pointpolicytype.PointPolicyTypeCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.pointpolicytype.PointPolicyTypeReadResponseDto;
import com.nhnacademy.inkbridge.front.service.PointPolicyTypeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * class: PointPolicyTypeService.
 *
 * @author jangjaehun
 * @version 2024/02/22
 */
@Service
@RequiredArgsConstructor
public class PointPolicyTypeServiceImpl implements PointPolicyTypeService {

    private final PointPolicyTypeAdaptor pointPolicyTypeAdapter;

    /**
     * 포인트 정책 유형을 생성하는 메소드입니다.
     *
     * @param requestDto PointPolicyTypeCreateRequestDto
     */
    @Override
    public void createPointPolicyType(PointPolicyTypeCreateRequestDto requestDto) {
        pointPolicyTypeAdapter.createPointPolicyType(requestDto);
    }

    /**
     * 포인트 정책 유형 리스트를 호출하는 메소드입니다.
     *
     * @return List - PointPolicyTypeReadResponseDto
     */
    @Override
    public List<PointPolicyTypeReadResponseDto> getPointPolicyTypes() {
        return pointPolicyTypeAdapter.getPointPolicyTypes();
    }
}
