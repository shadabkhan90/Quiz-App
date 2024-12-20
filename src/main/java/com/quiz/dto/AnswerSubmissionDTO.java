package com.quiz.dto;

import lombok.Data;

@Data
public class AnswerSubmissionDTO {
    private Long questionId;
    private String answer;
} 