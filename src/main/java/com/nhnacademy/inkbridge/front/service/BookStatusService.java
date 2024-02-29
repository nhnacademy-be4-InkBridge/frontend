package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.bookstatus.BookStatusReadResponseDto;
import java.util.List;

/**
 * class: BookStatusService.
 *
 * @author minm063
 * @version 2024/02/28
 */
public interface BookStatusService {
    List<BookStatusReadResponseDto> getBookStatuses();
}
