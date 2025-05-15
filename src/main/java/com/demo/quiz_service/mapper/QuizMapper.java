package com.demo.quiz_service.mapper;

import com.demo.quiz_service.dto.QuizDTO;
import com.demo.quiz_service.model.Quiz;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class QuizMapper {

    @Autowired
    private ModelMapper mapper;

    public QuizDTO convertToDTO(Quiz quiz) {
        if(!Objects.isNull(quiz))
            return mapper.map(quiz, QuizDTO.class);
        else
            return null;
    }

    public Quiz convertToModel(QuizDTO quizDTO) {
        if(!Objects.isNull(quizDTO))
            return mapper.map(quizDTO, Quiz.class);
        else
            return null;
    }
}
