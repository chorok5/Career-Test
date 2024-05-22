package com.kidwiz.web.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kidwiz.web.DTO.TestResult;


public interface TestResultRepository extends JpaRepository<TestResult, Integer> {

	void save(List<TestResult> testResults);
	List<TestResult> findByMemberId(int memberId);
	
	//  qid가 10인 최근의 테스트 결과를 반환하는 메서드
	List<TestResult> findByMemberIdAndQidOrderByTdateDesc(int memberId, int qid);
	
	// List<TestResult> findByMemberIdAndQidNotAndQidOrderByTdateDesc(int memberId, int qid, int qidToExclude);
	List<TestResult> findByMemberIdAndQidAndTdateNotInOrderByTdateDesc(int memberId, int qid, List<LocalDateTime> excludeDates);
	
    @Query("SELECT tr " +
            "FROM TestResult tr " +
            "WHERE tr.memberId = :memberId AND tr.qid = 10 " +
            "ORDER BY tr.tdate DESC")
     List<TestResult> findPreviousResultsByMemberId(@Param("memberId") int memberId);
 }
