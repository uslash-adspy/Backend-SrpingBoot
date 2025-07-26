package com.example.adSpy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public class AnalyzeRequest {
    private List<String> post; // 필드 이름을 "post"로 변경하고 List<String> 타입으로 설정

    // 생성자: 단일 URL을 받아 리스트에 담도록 합니다.
    public AnalyzeRequest(String url) {
        this.post = List.of(url); // Java 9+에서 List.of()를 사용하여 불변 리스트 생성
        // Java 8 이하에서는 new ArrayList<>(Arrays.asList(url)); 사용
    }

    // 또는 여러 URL을 받을 수 있는 생성자도 추가할 수 있습니다.
    public AnalyzeRequest(List<String> post) {
        this.post = post;
    }

    // Getter and Setter
    public List<String> getPost() {
        return post;
    }

    public void setPost(List<String> post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "AnalyzeRequest{" +
                "post=" + post +
                '}';
    }
}