package com.kidwiz.web.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

// ResultData(DTO) 클래스는 TestQuestion, TestAnswer, TestResult 엔티티 간의 데이터 전송을 위해 사용

@Data

public class ResultData {
    private String tcategory;
    private String ttitle;
    private String recommendedJobs;
    private List<String> personalTraits;
    private String description;
    private int totalScore;
    private LocalDateTime tdate;
    
}