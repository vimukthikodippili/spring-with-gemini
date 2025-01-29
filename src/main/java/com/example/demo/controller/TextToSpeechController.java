package com.example.demo.controller;

import com.example.demo.service.TextToSpeechService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/text-to-speech")
public class TextToSpeechController {

    private final TextToSpeechService textToSpeechService;

    public TextToSpeechController(TextToSpeechService textToSpeechService) {
        this.textToSpeechService = textToSpeechService;
    }

    @PostMapping("/synthesize")
    public ResponseEntity<String> synthesizeText(@RequestBody String text) {
        String response = textToSpeechService.synthesizeSpeech(text);
        return ResponseEntity.ok(response);
    }
}