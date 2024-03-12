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

    List<WrappingReadResponseDto> getWrappingList(boolean isActive);
}
