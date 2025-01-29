package com.example.demo.service;

import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
public class TextToSpeechService {

    public String synthesizeSpeech(String text) {
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {

            // Prepare the input text
            SynthesisInput input = SynthesisInput.newBuilder()
                    .setText(text)
                    .build();

            // Configure the voice settings
            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("en-US")
                    .setSsmlGender(SsmlVoiceGender.NEUTRAL)
                    .build();

            // Select the audio format
            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.MP3)
                    .build();

            // Perform text-to-speech synthesis
            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
            ByteString audioContents = response.getAudioContent();

            // Save the audio output to a file
            String outputFilePath = "output.mp3";
            try (OutputStream out = new FileOutputStream(outputFilePath)) {
                out.write(audioContents.toByteArray());
                System.out.println("Audio content written to file: " + outputFilePath);
            }

            return "Audio file generated: " + outputFilePath;
        } catch (Exception e) {
            throw new RuntimeException("Error generating speech", e);
        }
    }

}
