package com.kidwiz.web.DTO;

import java.time.LocalDateTime;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//@Entity 어노테이션을 사용하여 엔티티로 지정되었으므로 JPA(Hibernate)에 의해 데이터베이스 테이블로 생성

@Data
@Entity
@Table(name = "test_answer")
@Comment("심리검사 선택한 답변")
public class TestAnswer {

    // @Id를 붙여주면 기본 키 -> @Id가 2개면 복합 키로 저장됨!!

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sid", nullable = false)
    private Integer sid = 1;

    @Column(name = "qid", nullable = true)
    @Comment("질문 id")
    private Integer qid;

    @Column(name = "tanswer")
    @Comment("선택한 답변")
    private String tanswer;

    @Column(name = "tdate")
    @Comment("검사 실시일")
    private LocalDateTime tdate;
}
