package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.bookstatus.BookStatusReadResponseDto;
import java.util.List;

/**
 * class: BookStatusAdaptor.
 *
 * @author minm063
 * @version 2024/02/27
 */
public interface BookStatusAdaptor {
    List<BookStatusReadResponseDto> getBookStatuses();
}
