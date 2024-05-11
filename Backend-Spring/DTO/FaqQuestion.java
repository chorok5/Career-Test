package com.kidwiz.web.DTO;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "faq_question")
public class FaqQuestion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="question_id")
    private Long id;
	
	@Column(name="writer")
	private String writer;
	
    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    
    @Column(name="date") 
    private Date date; 
    
    // 기본 생성자 추가
    public FaqQuestion() {
    }
    public FaqQuestion(Long id, String title, String content, String writer, Date date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.date = date;
    }


    
}
