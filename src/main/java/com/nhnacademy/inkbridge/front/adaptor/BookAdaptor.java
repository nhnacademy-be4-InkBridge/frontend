package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.BookAdminReadResponse;
import com.nhnacademy.inkbridge.front.dto.BooksAdminReadResponse;
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
 * class: BookAdaptor.
 *
 * @author minm063
 * @version 2024/02/22
 */
@Component
public class BookAdaptor {

    private final RestTemplate restTemplate;

    public BookAdaptor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BooksAdminReadResponse> getBooksAdmin() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<List<BooksAdminReadResponse>> exchange = restTemplate.exchange(
            "http://localhost:8060/api/admin/books",
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders),
            new ParameterizedTypeReference<>() {
            });
        return exchange.getBody();
    }

    public BookAdminReadResponse getBookAdmin(Long bookId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<BookAdminReadResponse> exchange = restTemplate.exchange(
            "http://localhost:8060/api/admin/books/{bookId}",
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders),
            new ParameterizedTypeReference<>() {
            }, bookId);
        return exchange.getBody();
    }
}
