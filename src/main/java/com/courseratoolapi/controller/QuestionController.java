package com.courseratoolapi.controller;

import com.courseratoolapi.entity.dto.QuestionDto;
import com.courseratoolapi.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.util.List;

@RestController
public class QuestionController {

    private final QuestionService questionService;
    private final ObjectMapper objectMapper;
    public QuestionController(QuestionService questionService, ObjectMapper objectMapper) {
        this.questionService = questionService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/questions")
    public List<QuestionDto> getQuestions() {
        return questionService.getAllQuestions();
    }
    @PostMapping("/import")
    public String importQuestions(@RequestParam("file") MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            List<QuestionDto> questions = objectMapper.readValue(
                    is,
                    new TypeReference<>() {
                    }
            );
            questionService.saveQuestions(questions);
            return "Imported " + questions.size() + " questions successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to import: " + e.getMessage();
        }
    }
}
