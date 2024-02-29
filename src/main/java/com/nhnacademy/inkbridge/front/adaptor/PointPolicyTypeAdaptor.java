package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.pointpolicytype.PointPolicyTypeCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.pointpolicytype.PointPolicyTypeReadResponseDto;
import java.util.List;

/**
 * class: PointPolicyTypeAdaptor.
 *
 * @author jangjaehun
 * @version 2024/02/22
 */
public interface PointPolicyTypeAdaptor {

    void createPointPolicyType(PointPolicyTypeCreateRequestDto requestDto);

    List<PointPolicyTypeReadResponseDto> getPointPolicyTypes();
}
