package com.kidwiz.web.DTO;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "test_results")
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    @Column(name = "sid")
    private Integer sid;

    @Column(name = "tid")
    private Integer tid;

    @Column(name = "tscore")
    private Integer tscore;

    @Column(name = "tdate")
    private LocalDateTime tdate;
}
