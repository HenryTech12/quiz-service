package com.demo.quiz_service.repository;

import com.demo.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    Optional<Quiz> findByTitle(String title);
}
