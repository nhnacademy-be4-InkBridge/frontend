package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.BookAdaptor;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminDetailReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.book.BookAdminUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.book.BooksAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import java.io.IOException;
import java.net.URI;
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
import org.springframework.web.util.UriComponentsBuilder;

/**
 * class: BookAdaptorImpl.
 *
 * @author minm063
 * @version 2024/02/25
 */
@Component
public class BookAdaptorImpl implements BookAdaptor {

    private static final String DEFAULT_PATH = "/api/admin/books";
    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;

    public BookAdaptorImpl(RestTemplate restTemplate, GatewayProperties gatewayProperties) {
        this.restTemplate = restTemplate;
        this.gatewayProperties = gatewayProperties;
    }

    /**
     * {@inheritDoc}
     */
    public PageRequestDto<BooksAdminReadResponseDto> getBooksAdmin(Integer page, Integer size) {
        HttpHeaders httpHeaders = CommonUtils.createHeader();

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(DEFAULT_PATH)
            .queryParam("page", page)
            .queryParam("size", size)
            .encode()
            .build()
            .toUri();

        ResponseEntity<PageRequestDto<BooksAdminReadResponseDto>> exchange = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders),
            new ParameterizedTypeReference<>() {
            });
        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     */
    public BookAdminDetailReadResponseDto getBookAdmin(Long bookId) {
        HttpHeaders httpHeaders = CommonUtils.createHeader();

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(DEFAULT_PATH)
            .path("/{bookId}")
            .encode()
            .build()
            .expand(bookId)
            .toUri();
        ResponseEntity<BookAdminDetailReadResponseDto> exchange = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders),
            new ParameterizedTypeReference<>() {
            });
        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BookAdminReadResponseDto getBookAdmin() {
        HttpHeaders httpHeaders = CommonUtils.createHeader();

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(DEFAULT_PATH)
            .path("/form")
            .encode()
            .build()
            .toUri();

        ResponseEntity<BookAdminReadResponseDto> exchange = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders),
            new ParameterizedTypeReference<>() {
            });
        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     */
    public void createBookAdmin(MultipartFile thumbnail,
        BookAdminCreateRequestDto bookAdminCreateRequestDto)
        throws IOException {
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();

        HttpHeaders multipartHeader = getCreateMultipartAndJsonHeaders(thumbnail,
            bookAdminCreateRequestDto, multiValueMap);

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(DEFAULT_PATH)
            .encode()
            .build()
            .toUri();

        ResponseEntity<BookAdminCreateRequestDto> exchange = restTemplate.exchange(
            uri,
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
    public void updateBookAdmin(Long bookId, MultipartFile thumbnail,
        BookAdminUpdateRequestDto bookAdminUpdateRequestDto)
        throws IOException {
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();

        HttpHeaders multipartHeader = getUpdateMultipartAndJsonHeaders(thumbnail,
            bookAdminUpdateRequestDto, multiValueMap);

        URI uri = UriComponentsBuilder
            .fromUriString(gatewayProperties.getUrl())
            .path(DEFAULT_PATH)
            .path("/{bookId}")
            .encode()
            .build()
            .expand(bookId)
            .toUri();

        ResponseEntity<BookAdminCreateRequestDto> exchange = restTemplate.exchange(
            uri,
            HttpMethod.PUT,
            new HttpEntity<>(multiValueMap, multipartHeader),
            new ParameterizedTypeReference<>() {
            });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }


    /**
     * MULTIPART_FORM_DATA와 APPLICATION_JSON header를 생성합니다. MultiValueMap에 두 헤더로 만든 HttpEntity가 담기게
     * 됩니다.
     *
     * @param thumbnail                 MultipartFile
     * @param bookAdminCreateRequestDto BookAdminCreateRequestDto
     * @param multiValueMap             MultiValueMap
     * @return HttpHeaders
     * @throws IOException MultipartFile.getByte()
     */
    private HttpHeaders getCreateMultipartAndJsonHeaders(MultipartFile thumbnail,
        BookAdminCreateRequestDto bookAdminCreateRequestDto,
        MultiValueMap<String, Object> multiValueMap) throws IOException {
        HttpHeaders multipartHeader = CommonUtils.createHeader(MediaType.MULTIPART_FORM_DATA);

        fileToByteArrayResource(thumbnail, multiValueMap, multipartHeader);

        HttpHeaders jsonHeader = CommonUtils.createHeader();
        HttpEntity<BookAdminCreateRequestDto> requestEntityJson = new HttpEntity<>(
            bookAdminCreateRequestDto,
            jsonHeader);
        multiValueMap.add("book", requestEntityJson);
        return multipartHeader;
    }

    /**
     * MULTIPART_FORM_DATA와 APPLICATION_JSON header를 생성합니다. MultiValueMap에 두 헤더로 만든 HttpEntity가 담기게
     * 됩니다.
     *
     * @param thumbnail                 MultipartFile
     * @param bookAdminUpdateRequestDto BookAdminUpdateRequestDto
     * @param multiValueMap             MultiValueMap
     * @return HttpHeaders
     * @throws IOException MultipartFile.getByte()
     */
    private HttpHeaders getUpdateMultipartAndJsonHeaders(MultipartFile thumbnail,
        BookAdminUpdateRequestDto bookAdminUpdateRequestDto,
        MultiValueMap<String, Object> multiValueMap) throws IOException {
        HttpHeaders multipartHeader = CommonUtils.createHeader(MediaType.MULTIPART_FORM_DATA);

        fileToByteArrayResource(thumbnail, multiValueMap, multipartHeader);

        HttpHeaders jsonHeader = CommonUtils.createHeader();
        HttpEntity<BookAdminUpdateRequestDto> requestEntityJson = new HttpEntity<>(
            bookAdminUpdateRequestDto,
            jsonHeader);
        multiValueMap.add("book", requestEntityJson);
        return multipartHeader;
    }

    /**
     * MultipartFile을 ByteArrayResource로 래핑합니다.
     *
     * @param image           MultipartFile
     * @param multiValueMap   MultiValueMap
     * @param multipartHeader HttpHeaders
     * @throws IOException MultipartFile.getByte()
     */
    private void fileToByteArrayResource(MultipartFile image,
        MultiValueMap<String, Object> multiValueMap, HttpHeaders multipartHeader)
        throws IOException {
        if (image != null) {
            ByteArrayResource thumbnailAsResource = new ByteArrayResource(image.getBytes()) {
                @Override
                public String getFilename() {
                    return image.getOriginalFilename();
                }
            };
            multiValueMap.add("image", new HttpEntity<>(thumbnailAsResource, multipartHeader));
        }
    }
}
