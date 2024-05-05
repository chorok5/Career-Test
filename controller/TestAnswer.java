package com.kidwiz.web.controller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tests")


public class TestAnswer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;

    @Column(name = "rid")
    private Integer rid;

    @Column(name = "sid")
    private Integer sid;

    @Column(name = "ttitle")
    private String ttitle;

    @Column(name = "tquest")
    private String tquest;

    @Column(name = "tresult")
    private String tresult;
	
}

	
