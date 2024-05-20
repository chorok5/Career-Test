package com.kidwiz.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kidwiz.web.DTO.TestResult;


public interface TestResultRepository extends JpaRepository<TestResult, Integer> {

	void save(List<TestResult> testResults);
	List<TestResult> findByMemberId(int memberId);
	
	//  qid가 10인 최근의 테스트 결과를 반환하는 메서드
	//List<TestResult> findByMemberIdAndQidOrderByTdateDesc();
	List<TestResult> findByMemberIdAndQidOrderByTdateDesc(int memberId, int qid);
}