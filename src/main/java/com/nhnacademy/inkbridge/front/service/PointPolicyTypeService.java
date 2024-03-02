package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.pointpolicytype.PointPolicyTypeCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.pointpolicytype.PointPolicyTypeReadResponseDto;
import java.util.List;

/**
 * class: PointPolicyTypeService.
 *
 * @author jangjaehun
 * @version 2024/02/22
 */
public interface PointPolicyTypeService {

    void createPointPolicyType(PointPolicyTypeCreateRequestDto requestDto);

    List<PointPolicyTypeReadResponseDto> getPointPolicyTypes();
}
