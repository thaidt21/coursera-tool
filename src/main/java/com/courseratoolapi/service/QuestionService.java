package com.courseratoolapi.service;

import com.courseratoolapi.entity.Answer;
import com.courseratoolapi.entity.Question;
import com.courseratoolapi.entity.dto.QuestionDto;
import com.courseratoolapi.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<QuestionDto> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(q -> new QuestionDto(
                        q.getQuestionText(),
                        q.getType(),
                        q.getAnswers().stream()
                                .map(Answer::getAnswerText)
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
    public void saveQuestions(List<QuestionDto> questionDtos) {
        for (QuestionDto dto : questionDtos) {
            Question q = new Question();
            q.setQuestionText(dto.getQuestion());
            q.setType(dto.getType());

            List<Answer> answers = dto.getAnswers().stream()
                    .map(ans -> new Answer(null, ans, q))
                    .collect(Collectors.toList());

            q.setAnswers(answers);
            questionRepository.save(q);
        }
    }
}
