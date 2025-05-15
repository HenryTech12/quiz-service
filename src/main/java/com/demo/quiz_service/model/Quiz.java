package com.demo.quiz_service.model;

import com.demo.quiz_service.dto.QuestionDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private String title;
    @ElementCollection
    private List<QuestionDTO> questionDTOList;
    private String category;
    private String status;
    private int result;
}
