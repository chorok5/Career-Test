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
import com.kidwiz.web.DTO.TestResult;
import com.kidwiz.web.service.TestService;

@RestController
@RequestMapping("/api")
public class TestController {

	@Autowired
	TestService testService;

	// submitTest에서는 int로 qid, tanswer를 받아옴.
	@PostMapping("/submitTest")
	public ResponseEntity<ResultData> submitTest(@RequestBody List<Integer> questionData) {
	    List<TestResult> testResults = new ArrayList<>();
	    int totalScore = 0;
	    for (int i = 0; i < questionData.size(); i++) {
	        Integer answer = questionData.get(i);
	        if (answer != null) {
	            // 각 답변마다 qid와 함께 TestResult 객체를 생성하여 저장
	            TestResult testResult = new TestResult();
	            testResult.setQid(i + 1); // 질문의 인덱스를 qid로 설정
	            testResult.setTanswer(answer);
	            testResult.setTotalScore(answer); // 각 질문의 점수를 totalScore로 설정
	            testResult.setTdate(LocalDateTime.now()); // 현재 날짜와 시간 설정
	            testResults.add(testResult); // TestResult 객체 리스트에 추가

	            // 각 질문의 점수를 총 점수에 누적
	            totalScore += answer;
	        }
	    }

	    testService.saveTestResults(testResults);

	    // 추천 직업 및 성향 계산
	    String recommendedJobs = getRecommendedJobs(totalScore);
	    List<String> personalTraits = getPersonalTraits(totalScore);

	    ResultData result = new ResultData(); // ResultData 객체 생성
	    result.setTotalScore(totalScore);
	    result.setRecommendedJobs(recommendedJobs);
	    result.setPersonalTraits(personalTraits);
	    
	    return ResponseEntity.ok(result); // 클라이언트에게 응답을 보냄.
	}

	
	// getTestResult에서는 String으로 컨트롤러에 있는 직업,성향을 저장함.
	@PostMapping("/getTestResult")
	public ResponseEntity<ResultData> getTestResult(@RequestBody List<String> answers) {
	    // 문자열 리스트를 정수 리스트로 변환
	    List<Integer> intAnswers = new ArrayList<>();
	    for (String answer : answers) {
	        if (answer != null && !answer.isEmpty()) {
	            try {
	                intAnswers.add(Integer.parseInt(answer));
	            } catch (NumberFormatException e) {
	                // 정수로 변환할 수 없는 문자열이 포함된 경우 처리
	                // 예외가 발생해도 계속해서 다음 값들을 처리하도록 continue 사용
	                continue;
	            }
	        }
	    }
	    // 문자열 리스트를 그대로 결과로 사용
	    int totalScore = calculateTotalScore(intAnswers);
	    ResultData result = generateResultData(totalScore);
	    return ResponseEntity.ok(result);
	}
	
    private ResultData generateResultData(int totalScore) {
        ResultData result = new ResultData();
        return result;
    }

	
	private int calculateTotalScore(List<Integer> answers) {
	    int totalScore = 0;
	    for (Integer answer : answers) {
	        if (answer != null) {
	            // 답변이 null이 아닌 경우에만 점수를 계산하도록 수정
	            totalScore += answer; // 이미 정수이므로 변환할 필요 없음
	        } else {
	            // 답변이 null인 경우에는 점수를 계산하지 않음
	            System.out.println("답변이 null입니다.");
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
