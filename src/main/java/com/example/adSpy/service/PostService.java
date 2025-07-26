package com.example.adSpy.service;

import com.example.adSpy.dto.FormDto;
import com.example.adSpy.dto.PostDto;
import com.example.adSpy.entity.PostEntity;
import com.example.adSpy.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public PostEntity save(FormDto formDto) {
        Long id = null; // 외부 API가 'id'를 반환한다고 가정
        String category = "스포츠";
        Boolean isAd = true;
        Float backAdPercentage = 99.0f;
        Float adProbability = 31.0f;
        List<String> adUrls = new ArrayList<>();
        adUrls.add("광고 유알엘1");
        adUrls.add("광고 유알엘2");
        String comment = "이거 백퍼 광고임";

        PostDto postDto = new PostDto(id, formDto.getUrl(), category, isAd, backAdPercentage, adProbability, adUrls, comment);
        PostEntity postEntity = postDto.toEntity();
        return postRepository.save(postEntity);
    }

    public PostEntity getPost(String url) {
        return postRepository.findByUrl(url).orElse(null);
    }
}
