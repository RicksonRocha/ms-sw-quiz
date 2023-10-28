package com.example.msswquiz.controller;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.msswquiz.dto.QuestionResponseDTO;
import com.example.msswquiz.repository.QuestionRepository;

@RestController
@RequestMapping("api/question")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public Stream<QuestionResponseDTO> getAll() {
        Stream<QuestionResponseDTO> userList = questionRepository.findAll().stream().map(QuestionResponseDTO::new);
        return userList;
    }
}
