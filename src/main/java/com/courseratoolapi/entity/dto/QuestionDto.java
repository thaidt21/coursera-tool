package com.courseratoolapi.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class QuestionDto {

    @JsonProperty("Question")
    private String question;

    @JsonProperty("Type")
    private int type;

    @JsonProperty("Answers")
    private List<String> answers;

    // Constructor
    public QuestionDto(String question, int type, List<String> answers) {
        this.question = question;
        this.type = type;
        this.answers = answers;
    }

    // Getters and Setters
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
