package com.example.quizservice.controller;


import com.example.quizservice.entity.Quiz;
import com.example.quizservice.service.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping  //creatre
    public Quiz create(@RequestBody Quiz quiz) {
        return quizService.add(quiz);
    }

    //get all
    @GetMapping
    public List<Quiz> getAll() {
        return quizService.getAll();
    }

    @GetMapping("/{id}")
    public Quiz getById(@PathVariable Long id) {
        return quizService.getById(id);
    }
}
