package com.quizapp.service;

import com.quizapp.model.Question;
import com.quizapp.model.QuizSession;
import com.quizapp.repository.QuestionRepository;
import com.quizapp.repository.QuizSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private QuizSessionRepository quizSessionRepository;

    @Transactional
    public QuizSession startNewSession(String userId) {
        // Deactivate any existing active sessions for this user
        quizSessionRepository.deactivateUserSessions(userId);
        
        // Create new session
        QuizSession session = new QuizSession();
        session.setUserId(userId);
        session.setActive(true);
        session.setTotalQuestions(0);
        session.setCorrectAnswers(0);
        return quizSessionRepository.save(session);
    }

    public Question getRandomQuestion() {
        return questionRepository.findRandomQuestion();
    }

    @Transactional
    public boolean submitAnswer(String userId, Long questionId, String answer) {
        Question question = questionRepository.findById(questionId).orElse(null);
        List<QuizSession> activeSessions = quizSessionRepository.findByUserIdAndActive(userId, true);
        QuizSession session = activeSessions.isEmpty() ? null : activeSessions.get(0);
        
        if (question != null && session != null) {
            boolean isCorrect = question.getCorrectAnswer().equalsIgnoreCase(answer);
            
            // Update session statistics
            session.setTotalQuestions(session.getTotalQuestions() + 1);
            if (isCorrect) {
                session.setCorrectAnswers(session.getCorrectAnswers() + 1);
            }
            
            // Save session once with all updates
            quizSessionRepository.save(session);
            return isCorrect;
        }
        return false;
    }

    public QuizSession getSessionStats(String userId) {
        List<QuizSession> activeSessions = quizSessionRepository.findByUserIdAndActive(userId, true);
        return activeSessions.isEmpty() ? null : activeSessions.get(0);
    }
} 