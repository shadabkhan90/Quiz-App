package com.quizapp.repository;

import com.quizapp.model.QuizSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizSessionRepository extends JpaRepository<QuizSession, Long> {
    List<QuizSession> findByUserIdAndActive(String userId, boolean active);
    
    @Modifying
    @Query("UPDATE QuizSession q SET q.active = false WHERE q.userId = :userId AND q.active = true")
    void deactivateUserSessions(@Param("userId") String userId);
} 