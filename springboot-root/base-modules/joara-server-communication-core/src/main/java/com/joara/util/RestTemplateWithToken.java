package com.joara.util;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public final class RestTemplateWithToken {

    private final RestTemplate restTemplate;

    public <T> ResponseEntity<Optional<T>> getOptional(String uri, String accessToken, Class<T> clazz) {
        RequestEntity<?> requestEntity = prepareRequestEntity(uri, accessToken);

        // Send request & get response
        ResponseEntity<T> response =
                restTemplate.exchange(
                        requestEntity,
                        clazz
                );

        // 바디만 옵셔널로 변환, 상태코드는 그대로 전달.
        return new ResponseEntity<>(
                Optional.ofNullable(response.getBody()),
                response.getStatusCode()
        );
    }

    public <T> ResponseEntity<List<T>> getList(String uri, String accessToken, Class<T> clazz) {
        RequestEntity<?> requestEntity = prepareRequestEntity(uri, accessToken);

        // Send request & get response
        ResponseEntity<List> listResponse =
                restTemplate.exchange(
                        requestEntity,
                        List.class
                );

        return new ResponseEntity<>(
                (List<T>) listResponse,
                listResponse.getStatusCode()
        );
    }

    private RequestEntity<?> prepareRequestEntity(String uri, String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.add("Accept", "application/json");
        URI uri_ = URI.create(uri);

        return new RequestEntity<>(
                headers,
                HttpMethod.GET,
                uri_
        );
    }
}
