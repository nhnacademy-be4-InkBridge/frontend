package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.BookStatusAdaptor;
import com.nhnacademy.inkbridge.front.dto.bookstatus.BookStatusReadResponseDto;
import com.nhnacademy.inkbridge.front.service.BookStatusService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * class: BookStatusServiceImpl.
 *
 * @author minm063
 * @version 2024/02/28
 */
@Service
public class BookStatusServiceImpl implements BookStatusService {

    private final BookStatusAdaptor bookStatusAdaptor;

    public BookStatusServiceImpl(BookStatusAdaptor bookStatusAdaptor) {
        this.bookStatusAdaptor = bookStatusAdaptor;
    }

    @Override
    public List<BookStatusReadResponseDto> getBookStatuses() {
        return bookStatusAdaptor.getBookStatuses();
    }
}
