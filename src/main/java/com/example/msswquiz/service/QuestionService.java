package com.example.msswquiz.service;

import com.example.msswquiz.model.Question;
import com.example.msswquiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question getRandomQuestion() {
        List<Question> questions = questionRepository.findAll();
        int randomIndex = new Random().nextInt(questions.size());
        return questions.get(randomIndex);
    }
}
