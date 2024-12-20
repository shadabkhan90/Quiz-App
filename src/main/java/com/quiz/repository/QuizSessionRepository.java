package com.quiz.repository;

import com.quiz.model.QuizSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizSessionRepository extends JpaRepository<QuizSession, Long> {
    QuizSession findByUserIdAndActive(String userId, boolean active);
} 