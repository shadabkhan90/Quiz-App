package com.quiz.repository;

import com.quiz.model.UserResponse;
import com.quiz.model.QuizSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserResponseRepository extends JpaRepository<UserResponse, Long> {
    List<UserResponse> findByQuizSession(QuizSession session);
} 