package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.IndexAdaptor;
import com.nhnacademy.inkbridge.front.dto.book.BookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksReadResponseDto;
import com.nhnacademy.inkbridge.front.service.IndexService;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

/**
 * class: IndexServiceImpl.
 *
 * @author minm063
 * @version 2024/02/26
 */
@Service
public class IndexServiceImpl implements IndexService {

    private final IndexAdaptor indexAdaptor;

    public IndexServiceImpl(IndexAdaptor indexAdaptor) {
        this.indexAdaptor = indexAdaptor;
    }

    @Override
    public List<BooksReadResponseDto> getBooks() {
        return indexAdaptor.getBooks();
    }

    @Override
    public BookReadResponseDto getBook(Long bookId) {
        BookReadResponseDto book = indexAdaptor.getBook(bookId);
        String description = book.getDescription();
        Pattern pattern = Pattern.compile("!\\[image alt attribute\\((.*?)\\)");
        Matcher matcher = pattern.matcher(description);

        List<String> contents = new ArrayList<>();
        int textStart = 0;

        while (matcher.find()) {
            int start = matcher.start();
            String text = description.substring(textStart, start);
            contents.add(text);

            String image = matcher.group();
            contents.add(image);

            textStart = matcher.end();
        }

        if (textStart < description.length()) {
            String text = description.substring(textStart);
            contents.add(text);
        }

        book.setContents(contents);

        return book;
    }
}
