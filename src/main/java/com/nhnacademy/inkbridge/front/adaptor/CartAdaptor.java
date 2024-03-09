package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.cart.CartBookReadResponseDto;
import java.util.List;
import java.util.Set;

/**
 * class: CartAdaptor.
 *
 * @author minm063
 * @version 2024/03/09
 */
public interface CartAdaptor {
    List<CartBookReadResponseDto> getBook(Set<String> bookIdList);
}
