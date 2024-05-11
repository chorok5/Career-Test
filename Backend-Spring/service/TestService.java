package com.kidwiz.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidwiz.web.DTO.TestResult;
import com.kidwiz.web.repository.TestResultRepository;

@Service
public class TestService {


    @Autowired
    private TestResultRepository testResultRepository;


    @Transactional
    public void saveTestResult(TestResult testResult) {
        testResultRepository.save(testResult);
    }


    @Transactional
    public void saveTestResults(List<TestResult> testResults) {
        testResultRepository.saveAll(testResults);
    }

}