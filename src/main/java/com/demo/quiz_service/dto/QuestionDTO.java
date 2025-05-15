package com.demo.quiz_service.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Embeddable
public class QuestionDTO {

    private String question;
    private List<String> options;
    private String correctIndex;
    private String category;

}
