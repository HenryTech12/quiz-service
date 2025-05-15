package com.demo.quiz_service.service;

import com.demo.quiz_service.dto.QuestionDTO;
import com.demo.quiz_service.dto.QuizDTO;
import com.demo.quiz_service.dto.Status;
import com.demo.quiz_service.feign.QuizInterface;
import com.demo.quiz_service.mapper.QuizMapper;
import com.demo.quiz_service.model.Quiz;
import com.demo.quiz_service.repository.QuizRepository;
import com.demo.quiz_service.response.QuestionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizMapper quizMapper;
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizInterface quizInterface;

    private Logger logger = LoggerFactory.getLogger(QuizService.class);

    public void createQuiz(QuizDTO quizDTO) {
        if(!Objects.isNull(quizDTO)) {
            Quiz quiz = new Quiz();
            ResponseEntity<List<QuestionDTO>> questionDTOList = quizInterface.
                    getByCategoryAndNumOfQuestion(quizDTO.getCategory(), quizDTO.getNumOfQuestion());
            quiz = quizMapper.convertToModel(quizDTO);
            quiz.setQuestionDTOList(questionDTOList.getBody());
            quiz.setStatus(Status.NOT_COMPLETED.name());
            quizRepository.save(quiz);
            logger.info("question successfully added to db.");
        }
    }

    public QuizDTO getQuizByID(Long id) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(id);
        if(optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.orElse(new Quiz());
            return quizMapper.convertToDTO(quiz);
        }
        return null;
    }

    public QuizDTO getQuizByTitle(String title) {
        Optional<Quiz> optionalQuiz = quizRepository.findByTitle(title);
        if(optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.orElse(new Quiz());
            return quizMapper.convertToDTO(quiz);
        }
        return null;
    }

    public String removeByID(Long id) {
        quizRepository.deleteById(id);
        logger.info("quiz with id : {} removed successfully from db",id);
        return "quiz with id : "+id+" removed successfully from db";
    }

    public String removeByTitle(String title) {
        Optional<Quiz> optionalQuiz = quizRepository.findByTitle(title);
        if(optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.orElse(new Quiz());
            quizRepository.deleteById(quiz.getId());
            logger.info("quiz with title : {} removed successfully from db",quiz.getTitle());
            return "quiz with id : "+quiz.getId()+" removed successfully from db";
        }
        return "invalid id";
    }

    public String updateQuizById(Long id, QuizDTO quizDTO) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(id);
        if(optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.orElse(new Quiz());
            quiz = quizMapper.convertToModel(quizDTO);
            quizRepository.save(quiz);
            logger.info("quiz with id : {} details updated.",id);
            return "quiz with id : "+quiz.getId()+" details updated successfully";
        }
        logger.info("invalid id : {}",id);
        return "invalid id";
    }

    public String updateQuizByTitle(String title, QuizDTO quizDTO) {
        Optional<Quiz> optionalQuiz = quizRepository.findByTitle(title);
        if(optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.orElse(new Quiz());
            quiz = quizMapper.convertToModel(quizDTO);
            quizRepository.save(quiz);
            logger.info("quiz with title : {} details updated.",title);
            return "quiz with title : "+title+" details updated successfully";
        }
        logger.info("invalid title : {}",title);
        return "invalid title";
    }


    public String checkScore(QuestionResponse questionResponse) {
        ResponseEntity<String> response  = quizInterface.checkScore(questionResponse);
        logger.info(response.getBody());
        return response.getBody();
    }

    public String updateQuizStatus(Long id, String status) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(id);
        if(optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.orElse(new Quiz());
            quiz.setStatus(status);
            quizRepository.save(quiz);
            logger.info("quiz with id : {} status changed to {} .", id, quiz.getStatus());
            return "quiz with id : "+id+" status changed to "+ quiz.getStatus();
        }
        logger.info("invalid id : {}",id);
        return "invalid id";
    }
}
