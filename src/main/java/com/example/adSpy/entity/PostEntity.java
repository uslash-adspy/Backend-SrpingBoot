package com.example.adSpy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // DB에서 자동 생성될 ID

    private String url;
    private String category;
    private Boolean isAd;
    private Float backAdPercentage;
    private Float adProbability;

    @ElementCollection // List<String>과 같은 컬렉션을 매핑할 때 사용
    private List<String> adUrls = new ArrayList<>(); // 초기화하는 것이 좋습니다.

    private String comment;

    @Version // <--- 이 어노테이션을 추가하세요!
    private Long version; // <--- 이 필드를 추가하세요!

}