package com.example.msswquiz.controller;

import java.util.stream.Stream;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.msswquiz.service.QuestionService;
import com.example.msswquiz.service.StarWarsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.msswquiz.model.Question;
import com.example.msswquiz.dto.QuestionResponseDTO;
import com.example.msswquiz.repository.QuestionRepository;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private StarWarsApiService starWarsApiService;
    @GetMapping
    public Question getQuestion() {
        Question randomQuestion = questionService.getRandomQuestion();
        return starWarsApiService.fillQuestion(randomQuestion);
    }
}
