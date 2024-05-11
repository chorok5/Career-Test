package com.kidwiz.web.DTO;

import java.time.LocalDateTime;
import java.util.List;

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
@Comment("심리검사 결과 테이블(tno=테스트no, sid=학번)")
public class TestResult {
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
    @Comment("테스트 번호")
    private Integer tno;
    
    @Column(name = "sid", nullable = false)
    @Comment("학번")
    private Integer sid = 1;
	
    @Column(name="qid", nullable=false)
    @Comment("질문 id")
    private Integer qid;
    
	@Column(name = "tanswer", nullable = false) // vue 에서 value 를 받아와야함.
	@Comment("응답 value")
	private Integer tanswer;
    
    @Column(name = "total_score", nullable = false)
    @Comment("총점")
    private int totalScore;

    @Column(name = "tdate")
    @Comment("검사 실시일")
    private LocalDateTime tdate;

}
