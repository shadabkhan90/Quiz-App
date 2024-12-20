package com.quiz.service;

import com.quiz.model.Question;
import com.quiz.model.QuizSession;
import com.quiz.repository.QuestionRepository;
import com.quiz.repository.QuizSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private QuizSessionRepository quizSessionRepository;

    public QuizSession startNewSession(String userId) {
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

    public boolean submitAnswer(String userId, Long questionId, String answer) {
        Question question = questionRepository.findById(questionId).orElse(null);
        QuizSession session = quizSessionRepository.findByUserIdAndActive(userId, true);
        
        if (question != null && session != null) {
            session.setTotalQuestions(session.getTotalQuestions() + 1);
            if (question.getCorrectAnswer().equals(answer)) {
                session.setCorrectAnswers(session.getCorrectAnswers() + 1);
                quizSessionRepository.save(session);
                return true;
            }
            quizSessionRepository.save(session);
        }
        return false;
    }

    public QuizSession getSessionStats(String userId) {
        return quizSessionRepository.findByUserIdAndActive(userId, true);
    }
} 