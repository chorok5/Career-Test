package com.kidwiz.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kidwiz.web.DTO.ResultData;

@RestController
@RequestMapping("/api")
public class TestController {
    
	@PostMapping("/submitTest")
	public ResponseEntity<ResultData> submitTest(@RequestBody List<String> answers) {
		// POST 요청을 처리하는 메서드
	    ResultData result = analyzeAnswers(answers);
	    return ResponseEntity.ok(result);
	}


	@PostMapping("/getTestResult")
	public ResponseEntity<ResultData> getTestResult(@RequestBody List<String> answers) {
	    ResultData result = analyzeAnswers(answers);
	    return ResponseEntity.ok(result);
	}


    private ResultData analyzeAnswers(List<String> answers) {
    	ResultData resultData = new ResultData();

	    // 사용자 답변 분석
	    if (answers.contains("extrovert")) {
	        resultData.setTitle("외향적 성향");
	        resultData.setDescription("당신은 외향적인 성향을 가지고 있습니다. 대인 관계와 커뮤니케이션 능력이 뛰어나며, 사교적이고 활동적인 편입니다. 이러한 성향은 마케팅, 영업, 인사 관리 등의 직무에 적합할 수 있습니다.");
	    } else {
	        resultData.setTitle("내향적 성향");
	        resultData.setDescription("당신은 내향적인 성향을 가지고 있습니다. 집중력과 분석력이 뛰어나며, 독립적으로 업무를 수행하는 것을 선호합니다. 이러한 성향은 연구, 기술 개발, 데이터 분석 등의 직무에 적합할 수 있습니다.");
	    }

	    if (answers.contains("difficulty_adapting")) {
	        resultData.getPersonalTraits().add("변화에 적응하기 어려움");
	    } else {
	        resultData.getPersonalTraits().add("변화에 잘 적응함");
	    }

	    if (answers.contains("likes_teamwork")) {
	        resultData.getPersonalTraits().add("팀워크를 선호함");
	        resultData.getRecommendedJobs().add("프로젝트 매니저");
	        resultData.getRecommendedJobs().add("HR 전문가");
	    } else {
	        resultData.getPersonalTraits().add("독립적 업무 선호");
	        resultData.getRecommendedJobs().add("소프트웨어 개발자");
	        resultData.getRecommendedJobs().add("데이터 분석가");
	    }

	    return resultData;
	}
}

