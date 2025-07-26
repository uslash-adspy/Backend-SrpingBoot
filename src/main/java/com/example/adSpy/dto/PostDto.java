// PostDto.java (PostEntity에 @Version 추가 후 수정)
package com.example.adSpy.dto;

import com.example.adSpy.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString; // 사용하지 않으면 삭제

import java.util.List;

@AllArgsConstructor
@Data
public class PostDto {
    private Long id;
    private String url;
    private String category;
    private Boolean isAd;
    private Float backAdPercentage;
    private Float adProbability;
    private List<String> adUrls;
    private String comment;

    public PostEntity toEntity() {
        return PostEntity.builder()
                .id(id)
                .url(url)
                .category(category)
                .isAd(isAd)
                .backAdPercentage(backAdPercentage)
                .adProbability(adProbability)
                .adUrls(adUrls)
                .comment(comment)
                .build();
    }
}