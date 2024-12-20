package com.quiz.dto;

import lombok.Data;

@Data
public class QuizStatsDTO {
    private int totalQuestions;
    private int correctAnswers;
    private int incorrectAnswers;
    private double score;
} 