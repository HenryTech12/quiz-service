package com.demo.quiz_service.dto;

import lombok.Data;

@Data
public class QuizDTO {

    private String title;
    private String category;
    private int numOfQuestion;
    private String status;
}
