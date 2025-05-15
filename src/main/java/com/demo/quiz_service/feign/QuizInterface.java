package com.demo.quiz_service.feign;

import com.demo.quiz_service.dto.QuestionDTO;
import com.demo.quiz_service.response.QuestionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("/question/get/{category}/{numOfQuestion}")
    public ResponseEntity<List<QuestionDTO>> getByCategoryAndNumOfQuestion(@PathVariable String category, @PathVariable int numOfQuestion);

    @PostMapping("/question/check/grade")
    public ResponseEntity<String> checkScore(@RequestBody QuestionResponse questionResponse);

}
