package com.quiz.config;

import com.quiz.model.Question;
import com.quiz.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    
    @Bean
    CommandLineRunner initDatabase(QuestionRepository questionRepository) {
        return args -> {
            questionRepository.save(new Question(null, 
                "What is the capital of France?",
                "London", "Paris", "Berlin", "Madrid",
                "B"));
            questionRepository.save(new Question(null,
                "Which planet is known as the Red Planet?",
                "Venus", "Jupiter", "Mars", "Saturn",
                "C"));
            // Add more sample questions as needed
        };
    }
} 