package com.example.quizservice.service.impl;

import com.example.quizservice.entity.Quiz;
import com.example.quizservice.repository.QuizRepository;
import com.example.quizservice.service.QuestionClient;
import com.example.quizservice.service.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    private QuizRepository quizRepository;

    private QuestionClient questionClient;

    public QuizServiceImpl(QuizRepository quizRepository, QuestionClient questionClient) {
        this.quizRepository = quizRepository;
        this.questionClient = questionClient;
    }

    @Override
    public Quiz add(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> getAll() {
        List<Quiz> quizzes= quizRepository.findAll();

        List<Quiz> quizList = quizzes.stream().map(quiz -> {
            quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
            return quiz;
        }).collect(Collectors.toList());

        return quizList;
        //return quizRepository.findAll();
    }

    @Override
    public Quiz getById(Long id) {
        Quiz quiz= quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));

        return quiz;
    }
}
