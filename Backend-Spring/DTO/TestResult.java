package com.kidwiz.web.DTO;

import java.time.LocalDateTime;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Component
@Table(name = "test_results")
@Comment("심리검사 결과 테이블(tid=테스트id, sid=학번)")
// @IdClass(PrimaryKeys.class)
public class TestResult {
	
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
    private Long id;
    
    @Column(name = "sid", nullable = false)
    private Integer sid = 1;
	
    @Column(name="qid", nullable=true)
    @Comment("질문 id")
    private Integer qid;
    
    @Column(name = "recommendedJobs")
    private String recommendedJobs;

    @Column(name = "personalTraits")
    private String personalTraits;

    @Column(name = "totalScore")
    private int totalScore;

    @Column(name = "tdate")
    private LocalDateTime tdate;
}
