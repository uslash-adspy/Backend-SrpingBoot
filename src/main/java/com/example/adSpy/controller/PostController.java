package com.example.adSpy.controller;

import com.example.adSpy.dto.FormDto;
import com.example.adSpy.dto.PostDto;
import com.example.adSpy.entity.PostEntity;
import com.example.adSpy.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/api/getAnalyze")
    public ResponseEntity<PostEntity> geftPost(@RequestBody FormDto formDto) {
        PostEntity postEntity = postService.getPost(formDto.getUrl());
        return (postEntity != null)?
                ResponseEntity.status(HttpStatus.OK).body(postEntity) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/analyze")
    public ResponseEntity<PostEntity> getPost(@RequestBody FormDto formDto) {
        PostEntity postEntity = postService.save(formDto);
        return (postEntity != null)?
                ResponseEntity.status(HttpStatus.OK).body(postEntity) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
