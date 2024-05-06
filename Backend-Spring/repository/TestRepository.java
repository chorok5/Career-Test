package com.kidwiz.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kidwiz.web.DTO.TestAnswer;


public interface TestRepository extends JpaRepository<TestAnswer, Long> {

}
