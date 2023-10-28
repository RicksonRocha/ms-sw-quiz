package com.example.msswquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.msswquiz.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
