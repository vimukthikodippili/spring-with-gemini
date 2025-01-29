package com.example.demo.controller;

import com.example.demo.service.QnAService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/qna")
public class AIController {
    @Autowired
    private QnAService qnAService;

    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestBody Map<String, String> payload){
        String question = payload.get("question");
        String answer = qnAService.getAnswer(question);
        return ResponseEntity.ok(answer);
    }
}
