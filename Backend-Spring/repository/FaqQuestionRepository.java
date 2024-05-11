package com.kidwiz.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kidwiz.web.DTO.FaqQuestion;

public interface FaqQuestionRepository extends JpaRepository<FaqQuestion, Long>{

    Optional<FaqQuestion> findById(Long id);
    FaqQuestion save(FaqQuestion question);
    void deleteById(Long id);

}
