package com.demo.quiz_service.controller;

import com.demo.quiz_service.dto.QuizDTO;
import com.demo.quiz_service.response.QuestionResponse;
import com.demo.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/quiz")
@RestController
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO) {
        quizService.createQuiz(quizDTO);
        return new ResponseEntity<>("question successfully created.", HttpStatus.CREATED);
    }

    @GetMapping("/get/ID/{id}")
    public ResponseEntity<QuizDTO> getQuizByID(@PathVariable Long id) {
        return new ResponseEntity<>(quizService.getQuizByID(id), HttpStatus.OK);
    }

    @GetMapping("/get/title/{title}")
    public ResponseEntity<QuizDTO> getQuizByTitle(@PathVariable String title) {
        return new ResponseEntity<>(quizService.getQuizByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/remove/ID/{id}")
    public ResponseEntity<String> removeQuizUsingID(@PathVariable Long id) {
        return new ResponseEntity<>(quizService.removeByID(id), HttpStatus.OK);
    }

    @GetMapping("/remove/title/{title}")
    public ResponseEntity<String> removeQuizUsingTitle(@PathVariable String title) {
        return new ResponseEntity<>(quizService.removeByTitle(title), HttpStatus.OK);
    }

    @PostMapping("/update/ID/{id}")
    public ResponseEntity<String> updateQuizByID(@PathVariable Long id,@RequestBody  QuizDTO quizDTO) {
        return new ResponseEntity<>(quizService.updateQuizById(id, quizDTO), HttpStatus.OK);
    }

    @PostMapping("/update/title/{title}")
    public ResponseEntity<String> updateQuizByTitle(@PathVariable String title, @RequestBody QuizDTO quizDTO) {
        return new ResponseEntity<>(quizService.updateQuizByTitle(title,quizDTO), HttpStatus.OK);
    }

    @PostMapping("/score/check")
    public ResponseEntity<String> getQuizScore(@RequestBody QuestionResponse questionResponse) {
        return new ResponseEntity<>(quizService.checkScore(questionResponse), HttpStatus.OK);
    }
}
