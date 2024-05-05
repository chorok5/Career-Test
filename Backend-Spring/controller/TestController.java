package com.kidwiz.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kidwiz.web.DTO.ResultData;
import com.kidwiz.web.service.TestService;

@RestController
@RequestMapping("/api")
public class TestController {
    
	@Autowired
	TestService testService;
	
	@PostMapping("/submitTest")
	public ResponseEntity<ResultData> submitTest(@RequestBody List<String> answers) {
		// POST 요청을 처리하는 메서드
	    ResultData result = analyzeAnswers(answers);
	    
	 // 심리검사 답변 저장
        TestAnswer testAnswer = new TestAnswer();
        testAnswer.setTcategory(result.getCategory()); // 질문 제목 저장
        testAnswer.setTtitle(result.getTitle()); // 질문 제목 저장
        testService.saveTest(testAnswer);
	    
	    return ResponseEntity.ok(result);
	}


	@PostMapping("/getTestResult")
	public ResponseEntity<ResultData> getTestResult(@RequestBody List<String> answers) {
	    ResultData result = analyzeAnswers(answers);
	    
	    int totalScore = calculateTotalScore(answers);
	    result.setTotalScore(totalScore);
	    
	    return ResponseEntity.ok(result);
	}


    private ResultData analyzeAnswers(List<String> answers) {
    	ResultData resultData = new ResultData();

	    // 사용자 답변 분석
    	int totalScore = calculateTotalScore(answers);
        String recommendedJobs = getRecommendedJobs(totalScore);
        List<String> personalTraits = getPersonalTraits(answers);

        resultData.setRecommendedJobs(recommendedJobs);
        resultData.setPersonalTraits(personalTraits);

        return resultData;
	}
    
    private int calculateTotalScore(List<String> answers) {
        int totalScore = 0;
        for (String answer : answers) {
            try {
                int score = Integer.parseInt(answer);
                totalScore += score; // value가 정수로 주어지므로, 직접 점수에 더합니다.
            } catch (NumberFormatException e) {
                // answer가 정수로 파싱될 수 없는 경우, 예외 처리
                System.out.println("답변이 올바른 숫자 형식이 아닙니다: " + answer);
            }
        }
        return totalScore;
    }

    private String getRecommendedJobs(int totalScore) {
        if (totalScore >= 25) {
            return "프로젝트 매니저, 인사 담당자, 커뮤니케이션 전문가 등";
        } else if (totalScore >= 20 && totalScore < 25) {
            return "소프트웨어 엔지니어, 마케팅 전문가, 연구원 등";
        } else if (totalScore >= 15 && totalScore < 20) {
            return "데이터 분석가, 금융 분석가, 교육자 등";
        } else if (totalScore >= 10 && totalScore < 15) {
            return "영업 직군, 기술 지원자, 디자이너 등";
        } else if (totalScore >= 5 && totalScore < 10) {
            return "관리직, 사무 보조원, 서비스 업종 등";
        } else {
            return "특정 분야에 대한 전문가, 자유 영업자, 미술가 등";
        }
    }
    
    
    
    private List<String> getPersonalTraits(List<String> answers) {
        List<String> personalTraits = new ArrayList<>();
        if (answers.contains("extrovert")) {
            personalTraits.add("외향적 성향");
        } else {
            personalTraits.add("내향적 성향");
        }
        // 나머지 성향 파악 로직
        return personalTraits;
    }
}




