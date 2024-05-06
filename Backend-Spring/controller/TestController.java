package com.kidwiz.web.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kidwiz.web.DTO.ResultData;
import com.kidwiz.web.DTO.TestAnswer;
import com.kidwiz.web.DTO.TestQuestion;
import com.kidwiz.web.DTO.TestResult;
import com.kidwiz.web.service.TestService;

@RestController
@RequestMapping("/api")
public class TestController {

	@Autowired
	TestService testService;

	@Autowired
	TestResult testResult;
	
	@PostMapping("/submitTest")
	public ResponseEntity<ResultData> submitTest(@RequestBody List<String> answers) {
	    // 1. 답변 데이터로 심리검사 결과를 계산하거나 분석함.
		int totalScore = calculateTotalScore(answers);
	    ResultData result = analyzeAnswers(answers, totalScore);
	    
	    // 2. 데이터베이스에 질문과 답변을 저장.
	    saveTestQuestion(answers); // 질문 저장
	    saveTestAnswer(answers); // 답변 저장
	    saveTestResult(result, totalScore); // 결과 저장
	    
	    // 3. 클라이언트에게 응답을 보냄.
	    return ResponseEntity.ok(result);
	}

	private void saveTestQuestion(List<String> answers) { // 필요한 다른 데이터 설정 = qid, sid, tid
	    TestQuestion testQuestion = new TestQuestion();
	    testQuestion.setTcategory("카테고리 값");
	    testQuestion.setTtitle("질문 내용 값");
	    testService.saveTestQuestion(testQuestion);
	}

	private void saveTestAnswer(List<String> answers) {
	    TestAnswer testAnswer = new TestAnswer();
	    testAnswer.setTanswer(String.join(",", answers));
	    testAnswer.setTdate(LocalDateTime.now());
	    testService.saveTest(testAnswer);
	}

	private void saveTestResult(ResultData result, int totalScore) {
	    TestResult testResult = new TestResult();
	    // testResult.setSid(userId); // 사용자 ID 설정
	    // testResult.setTid(testAnswer.getTid()); // 질문 ID 설정
	    testResult.setRecommendedJobs(result.getRecommendedJobs());
	    testResult.setPersonalTraits(String.join(",", result.getPersonalTraits()));
	    testResult.setTotalScore(totalScore);
	    testResult.setTdate(LocalDateTime.now());
	    testService.saveTestResult(testResult);
	}

	@PostMapping("/getTestResult")
	public ResponseEntity<ResultData> getTestResult(@RequestBody List<String> answers) {
		int totalScore = calculateTotalScore(answers);
	    ResultData result = analyzeAnswers(answers, totalScore); 
		result.setTotalScore(totalScore);
		return ResponseEntity.ok(result);
	}

	private ResultData analyzeAnswers(List<String> answers, int totalScore) {
		ResultData resultData = new ResultData();

		// 사용자 답변 분석
	    String recommendedJobs = getRecommendedJobs(totalScore);
	    List<String> personalTraits = getPersonalTraits(totalScore);
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

	private List<String> getPersonalTraits(int totalScore) {
	    List<String> personalTraits = new ArrayList<>();

	    // 외향성 여부 분석
	    if (totalScore >= 20) {
	        personalTraits.add("외향성, 사교성이 높음, 타인과의 대화를 즐김");
	    } else if (totalScore >= 12 && totalScore < 20) {
	        personalTraits.add("외향성, 리더십 능력이 있음, 그룹 내에서 적극적인 역할을 수행");
	    } else {
	        personalTraits.add("내향성, 사색적 성향, 자신만의 생각에 깊이 몰입");
	    }

	    // 대인관계 성향 분석
	    if (totalScore >= 20) {
	        personalTraits.add("대인관계 조절 능력이 뛰어남, 갈등 해결 능력이 뛰어남, 타인의 감정을 잘 이해하고 공감함");
	    } else if (totalScore >= 12 && totalScore < 20) {
	        personalTraits.add("협업 능력이 뛰어남, 타인에 대한 이해가 높음, 다양한 의견을 수용하고 존중함");
	    } else {
	        personalTraits.add("개인주의적 성향, 독립적인 활동을 선호함, 자신의 영역을 보호하고 지킴");
	    }

	    // 문제 해결 능력 성향 분석
	    if (totalScore >= 20) {
	        personalTraits.add("문제 해결 능력이 뛰어남, 창의적인 사고를 가짐, 복잡한 문제에 대한 해결책을 찾는 능력이 있음");
	    } else if (totalScore >= 12 && totalScore < 20) {
	        personalTraits.add("문제 해결에 적극적, 논리적인 사고를 가짐, 문제를 해결하기 위해 체계적으로 접근함");
	    } else {
	        personalTraits.add("초보적인 문제 해결 능력을 갖추고 있음. 문제 해결에 어려움을 겪을 수 있음. 도움과 지원을 통해 능력을 키울 수 있음");
	    }

	    return personalTraits;
	}


}
