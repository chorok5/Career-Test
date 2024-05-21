package com.kidwiz.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kidwiz.web.DTO.FaqQuestion;
import com.kidwiz.web.repository.FaqQuestionRepository;

@Service
public class FaqQuestionService {

    @Autowired
    private FaqQuestionRepository faqQuestionRepository;

    public List<FaqQuestion> getAllQuestions() {
        return faqQuestionRepository.findAll();
    }

    public FaqQuestion getQuestionById(Long fid) {
        return faqQuestionRepository.findById(fid).orElse(null);
    }

    public void saveQuestion(FaqQuestion question) {
        faqQuestionRepository.save(question);
    }

    public void updateQuestion(Long id, FaqQuestion updatedQuestion) {
        Optional<FaqQuestion> optionalQuestion = faqQuestionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            FaqQuestion question = optionalQuestion.get();
            question.setTitle(updatedQuestion.getTitle());
            question.setContent(updatedQuestion.getContent());
            question.setWriter(updatedQuestion.getWriter());
            question.setDate(updatedQuestion.getDate());
            faqQuestionRepository.save(question);
        }
    }

    public void deleteQuestion(Long id) {
        faqQuestionRepository.deleteById(id);
    }
    
	public Page<FaqQuestion> getQuestionsPaged(Pageable pageable) {
		return faqQuestionRepository.findAll(pageable);
	}
}

