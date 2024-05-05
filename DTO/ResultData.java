package com.kidwiz.web.DTO;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "test_results")
@Data
public class ResultData {   
    
    public ResultData() {
        this.personalTraits = new ArrayList<>();
        this.recommendedJobs = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    private String title; // 결과 제목

    @Column(columnDefinition = "TEXT")
    private String description; // 결과 설명

    @ElementCollection
    private List<String> recommendedJobs; // 추천 직업 리스트

    @ElementCollection
    private List<String> personalTraits; // 성향 리스트
 
    
}
