package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.FileAdaptor;
import com.nhnacademy.inkbridge.front.dto.book.BookFileReadResponseDto;
import java.io.IOException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: FileAdaptorImpl.
 *
 * @author minm063
 * @version 2024/02/29
 */
@Component
public class FileAdaptorImpl implements FileAdaptor {

    private final RestTemplate restTemplate;

    public FileAdaptorImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BookFileReadResponseDto uploadFile(MultipartFile image) throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();

        fileToByteArrayResource(image, multiValueMap, httpHeaders);

        ResponseEntity<BookFileReadResponseDto> exchange = restTemplate.exchange(
            "http://localhost:8060/api/images",
            HttpMethod.POST,
            new HttpEntity<>(multiValueMap, httpHeaders),
            new ParameterizedTypeReference<>() {
            }
        );

        return exchange.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] loadFile(String fileName) {
        HttpHeaders httpHeaders = new HttpHeaders();

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<byte[]> exchange = restTemplate.exchange(
            "http://localhost:8060/api/images?fileName={fileName}",
            HttpMethod.GET,
            requestEntity,
            new ParameterizedTypeReference<>() {
            }, fileName);
        if (!exchange.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException();
        }

        return exchange.getBody();
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
