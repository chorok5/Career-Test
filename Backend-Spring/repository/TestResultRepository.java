package com.kidwiz.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kidwiz.web.DTO.TestResult;


public interface TestResultRepository extends JpaRepository<TestResult, Integer> {

	void save(List<TestResult> testResults);
}