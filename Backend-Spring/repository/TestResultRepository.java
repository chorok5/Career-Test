package com.kidwiz.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kidwiz.web.DTO.TestResult;


public interface TestResultRepository extends JpaRepository<TestResult, Long> {

}
