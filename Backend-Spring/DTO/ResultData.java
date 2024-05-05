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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    private String category;
    
    private String title; // 결과 제목

    private String recommendedJobs;
    
    
    @ElementCollection
    private List<String> personalTraits = new ArrayList<>();
    
    @Column(columnDefinition = "TEXT")
    private String description; // 결과 설명

    private int totalScore; // 총점

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void setPersonalTraits(List<String> personalTraits) {
        this.personalTraits = personalTraits;
    }

}

    
