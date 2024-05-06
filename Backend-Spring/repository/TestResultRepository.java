package com.kidwiz.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kidwiz.web.DTO.PrimaryKeys;
import com.kidwiz.web.DTO.TestQuestion;
import com.kidwiz.web.DTO.TestResult;


public interface TestResultRepository extends JpaRepository<TestResult, PrimaryKeys> {

	void save(TestQuestion testQuestion);

}
