package com.kidwiz.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidwiz.web.DTO.TestAnswer;
import com.kidwiz.web.DTO.TestQuestion;
import com.kidwiz.web.DTO.TestResult;
import com.kidwiz.web.repository.TestRepository;
import com.kidwiz.web.repository.TestResultRepository;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestResultRepository testResultRepository;

    @Transactional
    public void saveTest(TestAnswer testAnswer) {
        testRepository.save(testAnswer);
    }

    @Transactional
    public void saveTestResult(TestResult testResult) {
        testResultRepository.save(testResult);
    }

    
    @Transactional
	public void saveTestQuestion(TestQuestion testQuestion) {
    	testResultRepository.save(testQuestion);
	}
}