package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.BookAdminReadResponse;
import com.nhnacademy.inkbridge.front.dto.BookAdminRequest;
import com.nhnacademy.inkbridge.front.dto.BooksAdminReadResponse;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: BookAdaptor.
 *
 * @author minm063
 * @version 2024/02/22
 */
@Component
public class BookAdaptor {

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;

    public BookAdaptor(RestTemplate restTemplate, GatewayProperties gatewayProperties) {
        this.restTemplate = restTemplate;
        this.gatewayProperties = gatewayProperties;
    }

    public List<BooksAdminReadResponse> getBooksAdmin() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<List<BooksAdminReadResponse>> exchange = restTemplate.exchange(
//            gatewayProperties.getUrl()+"/api/admin/books",
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


    public void createBookAdmin(MultipartFile thumbnail, MultipartFile[] bookImages,
        BookAdminRequest bookAdminRequest) throws IOException {
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();

        HttpHeaders multipartHeader = new HttpHeaders();
        multipartHeader.setContentType(MediaType.MULTIPART_FORM_DATA);

        if (thumbnail != null) {
            ByteArrayResource thumbnailAsResource = new ByteArrayResource(thumbnail.getBytes()) {
                @Override
                public String getFilename() {
                    return thumbnail.getOriginalFilename();
                }
            };
            multiValueMap.add("thumbnail", new HttpEntity<>(thumbnailAsResource, multipartHeader));
        }

        if (bookImages != null) {
            for (MultipartFile file : bookImages) {
                ByteArrayResource bookImageAsResource = new ByteArrayResource(file.getBytes()) {
                    @Override
                    public String getFilename() {
                        return file.getOriginalFilename();
                    }
                };
                HttpHeaders imageHeaders = new HttpHeaders();
                imageHeaders.setContentType(MediaType.IMAGE_PNG);
                multiValueMap.add("bookImages", new HttpEntity<>(bookImageAsResource, imageHeaders));
            }
        }

        HttpHeaders jsonHeader = new HttpHeaders();
        jsonHeader.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<BookAdminRequest> requestEntityJson = new HttpEntity<>(bookAdminRequest, jsonHeader);
        multiValueMap.add("book", requestEntityJson);

        ResponseEntity<BookAdminRequest> exchange = restTemplate.exchange(
            "http://localhost:8060/api/admin/books",
            HttpMethod.POST,
            new HttpEntity<>(multiValueMap, multipartHeader),
            new ParameterizedTypeReference<>() {
            });

        if (exchange.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }
    }


    public void updateBookAdmin(MultipartFile thumbnail, MultipartFile[] bookImages,
        BookAdminRequest bookAdminRequest) {
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//        headers.setAccept(List.of(MediaType.MULTIPART_FORM_DATA));

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        if (bookImages != null && bookImages.length != 0) {
            Arrays.asList(bookImages).forEach(file -> body.add("bookImages", file));
        }
        body.add("thumbnail", thumbnail);
        body.add("book", bookAdminRequest);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<BookAdminRequest> exchange = restTemplate.exchange(
            "localhost:8060/api/admin/books ",
            HttpMethod.PUT,
            requestEntity,
            new ParameterizedTypeReference<>() {
            });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

}
