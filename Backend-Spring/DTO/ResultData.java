package com.kidwiz.web.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

// ResultData(DTO) 클래스는 TestQuestion, TestAnswer, TestResult 엔티티 간의 데이터 전송을 위해 사용

@Data

public class ResultData {
	
	private List<String> answers;
    
	private int totalScore;
    private String recommendedJobs;
    private List<String> personalTraits;
    
    private LocalDateTime tdate;

}