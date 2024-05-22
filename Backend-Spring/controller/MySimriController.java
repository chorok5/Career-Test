package com.kidwiz.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.kidwiz.web.DTO.TestResult;
import com.kidwiz.web.repository.TestResultRepository;
import com.kidwiz.web.service.JwtService;

@RestController
public class MySimriController {

	private static final int QID = 10;
	
	@Autowired
	private TestResultRepository testResultRepository;
	
    @Autowired
    private JwtService jwtService;
    
    @GetMapping("/api/mysimri")
    public ResponseEntity<TestResult> getTestResultsForQid(
    		@CookieValue(value = "token", required = false) String token ) {
        
    	System.out.println("심리결과 토큰 확인: " + token);
        
    	if (!jwtService.isValid(token)) {
    		System.out.println("유효한가요?");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        int memberId = jwtService.getId(token);
        List<TestResult> testResults = testResultRepository.findByMemberIdAndQidOrderByTdateDesc(memberId, 10);
        
        System.out.println("list 출력??? : "+testResults);
        
        // 조회된 TestResult가 없을 경우
        if (testResults.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        // 여기서는 가장 최근의 TestResult를 반환하도록 함
        TestResult latestTestResult = testResults.get(0);
        return ResponseEntity.ok(latestTestResult);
    }
    
    
    @GetMapping("/api/mysimri/recommendations")
    public ResponseEntity<Map<String, Object>> getRecommendations(@RequestParam(name = "totalScore") int totalScore) {
        Map<String, Object> response = new HashMap<>();
        response.put("recommendedJobs", getRecommendedJobs(totalScore));
        response.put("personalTraits", getPersonalTraits(totalScore));
        System.out.println("response 출력 확인 : "+response);
        return ResponseEntity.ok(response);
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

        if (totalScore >= 20) {
            personalTraits.add("외향성, 사교성이 높음, 타인과의 대화를 즐김");
            personalTraits.add("대인관계 조절 능력이 뛰어남, 갈등 해결 능력이 뛰어남, 타인의 감정을 잘 이해하고 공감함");
            personalTraits.add("문제 해결 능력이 뛰어남, 창의적인 사고를 가짐, 복잡한 문제에 대한 해결책을 찾는 능력이 있음");
        } else if (totalScore >= 12 && totalScore < 20) {
            personalTraits.add("외향성, 리더십 능력이 있음, 그룹 내에서 적극적인 역할을 수행");
            personalTraits.add("협업 능력이 뛰어남, 타인에 대한 이해가 높음, 다양한 의견을 수용하고 존중함");
            personalTraits.add("문제 해결에 적극적, 논리적인 사고를 가짐, 문제를 해결하기 위해 체계적으로 접근함");
        } else {
            personalTraits.add("내향성, 사색적 성향, 자신만의 생각에 깊이 몰입");
            personalTraits.add("개인주의적 성향, 독립적인 활동을 선호함, 자신의 영역을 보호하고 지킴");
            personalTraits.add("초보적인 문제 해결 능력을 갖추고 있음. 문제 해결에 어려움을 겪을 수 있음. 도움과 지원을 통해 능력을 키울 수 있음");
        }

        return personalTraits;
    }
    
    @GetMapping("/api/mysimri/history")
    public ResponseEntity<List<TestResult>> getTestResultHistory(@CookieValue(value = "token", required=false) String token) {
        if (!jwtService.isValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        int memberId = jwtService.getId(token);
        List<TestResult> testResults = testResultRepository.findPreviousResultsByMemberId(memberId);
        
        if (testResults.size() > 1) {
            testResults = testResults.subList(1, testResults.size()); // 가장 최근 날짜의 결과를 제외한 나머지 결과
        } else {
            testResults = new ArrayList<>(); // 과거 기록이 없는 경우 빈 리스트 반환
        }
        
        System.out.println("과거 기록 list 출력: " + testResults);
        
        return ResponseEntity.ok(testResults);
    }
}