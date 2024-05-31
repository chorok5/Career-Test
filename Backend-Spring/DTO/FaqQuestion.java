package com.kidwiz.web.DTO;

import java.util.Date;

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
@Table(name = "faq_question")
public class FaqQuestion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;
	
	@Column(name="writer")
	private String writer;
	
    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    
    @Column(name="date") 
    private Date date; 
    
    @Column(name="faq_del")
    @Comment("0=삭제")
    private int faqdel;
    
    
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}


    
    public void setNumber(Long number) {
        this.id = number;
    }
    
//    public void setQuestionId(Long questionId) {
//        this.id = questionId;
//      }

}


