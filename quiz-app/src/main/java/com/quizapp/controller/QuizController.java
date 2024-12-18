package com.quizapp.controller  ;

import com.quizapp.model.Question;
import com.quizapp.model.QuizSession;
import com.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class QuizController {
    @Autowired
    private QuizService quizService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/start")
    public String startQuiz(@RequestParam String userId, Model model) {
        QuizSession session = quizService.startNewSession(userId);
        model.addAttribute("session", session);
        return "redirect:/question?userId=" + userId;
    }

    @GetMapping("/question")
    public String getQuestion(@RequestParam String userId, Model model) {
        Question question = quizService.getRandomQuestion();
        model.addAttribute("question", question);
        model.addAttribute("userId", userId);
        return "question";
    }

    @PostMapping("/submit")
    public String submitAnswer(@RequestParam String userId, 
                             @RequestParam Long questionId, 
                             @RequestParam String answer, 
                             Model model) {
        boolean isCorrect = quizService.submitAnswer(userId, questionId, answer);
        model.addAttribute("isCorrect", isCorrect);
        return "redirect:/question?userId=" + userId;
    }

    @GetMapping("/stats")
    public String getStats(@RequestParam String userId, Model model) {
        QuizSession stats = quizService.getSessionStats(userId);
        model.addAttribute("stats", stats);
        return "stats";
    }
} 