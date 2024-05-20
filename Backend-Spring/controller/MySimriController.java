package com.kidwiz.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.kidwiz.web.DTO.TestResult;
import com.kidwiz.web.repository.TestResultRepository;
import com.kidwiz.web.service.JwtService;

@RestController
public class MySimriController {

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
        
        System.out.println("list 출력"+testResults);
        
        // 조회된 TestResult가 없을 경우
        if (testResults.isEmpty()) {
        	System.out.println("조회된 TestResult가 없음");
            return ResponseEntity.notFound().build();
        }
        
        // 여기서는 가장 최근의 TestResult를 반환하도록 함
        TestResult latestTestResult = testResults.get(0);
        return ResponseEntity.ok(latestTestResult);
    }
}