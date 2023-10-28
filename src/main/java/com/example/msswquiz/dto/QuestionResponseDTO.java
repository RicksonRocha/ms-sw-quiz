package com.example.msswquiz.dto;

import com.example.msswquiz.model.Question;

public record QuestionResponseDTO(Long id, String description) {
    public QuestionResponseDTO(Question question) {
        this(question.getId(), question.getDescription());
    }
}
