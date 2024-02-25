package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.BookAdaptor;
import com.nhnacademy.inkbridge.front.dto.BookAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.BookAdminRequestDto;
import com.nhnacademy.inkbridge.front.dto.BooksAdminReadResponseDto;
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
 * class: BookAdaptorImpl.
 *
 * @author minm063
 * @version 2024/02/25
 */
@Component
public class BookAdaptorImpl implements BookAdaptor {

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;

    public BookAdaptorImpl(RestTemplate restTemplate, GatewayProperties gatewayProperties) {
        this.restTemplate = restTemplate;
        this.gatewayProperties = gatewayProperties;
    }

    /**
     * {@inheritDoc}
     */
    public List<BooksAdminReadResponseDto> getBooksAdmin() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<List<BooksAdminReadResponseDto>> exchange = restTemplate.exchange(
//            gatewayProperties.getUrl() + "/api/admin/books",
            "http://localhost:8060/api/admin/books",
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders),
            new ParameterizedTypeReference<>() {
            });
        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     */
    public BookAdminReadResponseDto getBookAdmin(Long bookId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<BookAdminReadResponseDto> exchange = restTemplate.exchange(
            "http://localhost:8060/api/admin/books/{bookId}",
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders),
            new ParameterizedTypeReference<>() {
            }, bookId);
        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     */
    public void createBookAdmin(MultipartFile thumbnail, MultipartFile[] bookImages,
        BookAdminRequestDto bookAdminRequestDto) throws IOException {
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();

        HttpHeaders multipartHeader = new HttpHeaders();
        multipartHeader.setContentType(MediaType.MULTIPART_FORM_DATA);

        thumbnailToByteArrayResource(thumbnail, multiValueMap, multipartHeader);
        bookImageToByteArrayResource(bookImages, multiValueMap);

        HttpHeaders jsonHeader = new HttpHeaders();
        jsonHeader.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<BookAdminRequestDto> requestEntityJson = new HttpEntity<>(bookAdminRequestDto,
            jsonHeader);
        multiValueMap.add("book", requestEntityJson);

        ResponseEntity<BookAdminRequestDto> exchange = restTemplate.exchange(
            "http://localhost:8060/api/admin/books",
            HttpMethod.POST,
            new HttpEntity<>(multiValueMap, multipartHeader),
            new ParameterizedTypeReference<>() {
            });

        if (exchange.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void updateBookAdmin(MultipartFile thumbnail, MultipartFile[] bookImages,
        BookAdminRequestDto bookAdminRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        if (bookImages != null && bookImages.length != 0) {
            Arrays.asList(bookImages).forEach(file -> body.add("bookImages", file));
        }
        body.add("thumbnail", thumbnail);
        body.add("book", bookAdminRequestDto);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<BookAdminRequestDto> exchange = restTemplate.exchange(
            "localhost:8060/api/admin/books ",
            HttpMethod.PUT,
            requestEntity,
            new ParameterizedTypeReference<>() {
            });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

    /**
     * {@inheritDoc}
     */
    private void thumbnailToByteArrayResource(MultipartFile thumbnail,
        MultiValueMap<String, Object> multiValueMap, HttpHeaders multipartHeader)
        throws IOException {
        if (thumbnail != null) {
            ByteArrayResource thumbnailAsResource = new ByteArrayResource(thumbnail.getBytes()) {
                @Override
                public String getFilename() {
                    return thumbnail.getOriginalFilename();
                }
            };
            multiValueMap.add("thumbnail", new HttpEntity<>(thumbnailAsResource, multipartHeader));
        }
    }

    /**
     * {@inheritDoc}
     */
    private void bookImageToByteArrayResource(MultipartFile[] bookImages,
        MultiValueMap<String, Object> multiValueMap) throws IOException {
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
                multiValueMap.add("bookImages",
                    new HttpEntity<>(bookImageAsResource, imageHeaders));
            }
        }
    }
}
