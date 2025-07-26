// src/main/java/com/example/adSpy/dto/ApiResponse.java
package com.example.adSpy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ApiResponse {
    private String category;
    @JsonProperty("isAd")
    private Boolean isAd;
    private Float backAdPercentage; // int (외부 API가 정수로 반환한다고 가정)
    private Float adProbability;    // int (외부 API가 정수로 반환한다고 가정)
    private List<String> adUrls;  // List<String>
    private String comment;

}