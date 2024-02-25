package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.IndexAdaptor;
import com.nhnacademy.inkbridge.front.dto.BookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.BooksReadResponseDto;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * class: IndexAdaptorImpl.
 *
 * @author minm063
 * @version 2024/02/26
 */
@Component
public class IndexAdaptorImpl implements IndexAdaptor {

    private final RestTemplate restTemplate;

    public IndexAdaptorImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BooksReadResponseDto> getBooks() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        ResponseEntity<List<BooksReadResponseDto>> exchange = restTemplate.exchange(
            "http://localhost:8060/api/books",
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders),
            new ParameterizedTypeReference<>() {
            }
        );

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BookReadResponseDto getBook(Long bookId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        ResponseEntity<BookReadResponseDto> exchange = restTemplate.exchange(
            "http://localhost:8060/api/books/{bookId}",
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders),
            new ParameterizedTypeReference<>() {
            }, bookId
        );

        return exchange.getBody();
    }
}
