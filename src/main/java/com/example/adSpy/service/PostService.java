package com.example.adSpy.service;

import com.example.adSpy.dto.*;
import com.example.adSpy.entity.PostEntity;
import com.example.adSpy.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient; // 추가
import reactor.core.publisher.Mono; // 추가

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final WebClient webClient; // WebClient 주입을 위한 필드

    // 생성자 주입 방식으로 WebClient를 받습니다.
    // Spring Boot는 자동으로 WebClient.Builder 빈을 제공합니다.
    public PostService(PostRepository postRepository, WebClient.Builder webClientBuilder) {
        this.postRepository = postRepository;
        this.webClient = webClientBuilder.baseUrl("http://localhost:8000") // 기본 URL 설정
                .build();
    }

    public PostEntity save(FormDto formDto) {
        // 1. 외부 API로 POST 요청을 보낼 요청 DTO 생성
        AnalyzeRequest analyzeRequest = new AnalyzeRequest(formDto.getUrl());

        // 2. WebClient를 사용하여 POST 요청 실행
        // Mono<ApiResponse>는 비동기적으로 ApiResponse를 반환할 것임을 의미합니다.
        ApiResponse apiResponse = webClient.post()
                .uri("/analyze/") // /analyze/ 엔드포인트
                .body(Mono.just(analyzeRequest), AnalyzeRequest.class) // 요청 본문 설정
                .retrieve() // 응답 검색 시작
                .bodyToMono(ApiResponse.class) // 응답 본문을 ApiResponse 객체로 변환
                .block(); // !!! 중요: 여기서는 동기적으로 결과를 기다립니다. (주의해서 사용)

        // `block()`은 Mono가 결과를 반환할 때까지 현재 스레드를 차단합니다.
        // Spring WebFlux 환경이 아닌 일반 Spring MVC 환경에서 비동기 작업을 동기적으로 처리할 때 유용하지만,
        // 블로킹을 최소화해야 하는 리액티브 애플리케이션에서는 주의해서 사용해야 합니다.
        // 만약 이 메서드 자체가 비동기적으로 동작해야 한다면 `Mono<PostEntity>`를 반환하도록 변경해야 합니다.

        // 3. API 응답 데이터를 사용하여 PostDto 생성 (더미 데이터 대신 실제 응답 사용)
        Long id = null; // 외부 API가 id를 반환하지 않는 경우 null 또는 적절한 값 설정
        String category = apiResponse.getCategory();
        Boolean isAd = apiResponse.getIsAd();
        Float backAdPercentage = apiResponse.getBackAdPercentage(); // int를 float로 형변환
        Float adProbability = apiResponse.getAdProbability(); // int를 float로 형변환
        List<String> adUrls = apiResponse.getAdUrls();
        String comment = apiResponse.getComment();

        PostDto postDto = new PostDto(id, formDto.getUrl(), category, isAd, backAdPercentage, adProbability, adUrls, comment);
        PostEntity postEntity = postDto.toEntity();
        return postRepository.save(postEntity);
    }

    public PostEntity getPost(String url) {
        return postRepository.findByUrl(url).orElse(null);
    }
}