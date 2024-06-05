package com.kidwiz.web.DTO;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "test_question")
@Comment("심리검사 카테고리와 질문(tid=테스트id, qid=질문번호)")
public class TestQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qid", nullable = true)
    @Comment("질문 id")
    private Integer qid;

    @Column(name = "tcategory")
    @Comment("카테고리")
    private String tcategory;

    @Column(name = "ttitle")
    @Comment("질문 내용")
    private String ttitle;

}
