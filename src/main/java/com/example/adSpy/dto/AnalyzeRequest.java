package com.example.adSpy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class AnalyzeRequest {
    private String content; // 필드 이름을 "post"로 변경하고 List<String> 타입으로 설정

    // 생성자: 단일 URL을 받아 리스트에 담도록 합니다.
    public AnalyzeRequest(String url) {
        this.content = url;
        // Java 8 이하에서는 new ArrayList<>(Arrays.asList(url)); 사용
    }

    @Override
    public String toString() {
        return "AnalyzeRequest{" +
                "content=" + content +
                '}';
    }
}