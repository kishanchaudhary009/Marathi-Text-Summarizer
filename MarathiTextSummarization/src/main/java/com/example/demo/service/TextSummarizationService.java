package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TextSummarizationService {

    private String apiKey = "your-gpt-apikey-here";

    private final RestTemplate restTemplate;

    public TextSummarizationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateAbstractiveSummary(String prompt) {
        System.out.println(apiKey);
        System.out.println("Generating summary with prompt: " + prompt);

        // Prepare the request to the ChatGPT API
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo-0125");
        requestBody.put("max_tokens", 2200); // Adjust max_tokens as needed

        // Include the 'messages' property in the request body
        List<Map<String, Object>> messages = Arrays.asList(
                new HashMap<String, Object>() {{
                    put("role", "system");
                    put("content", prompt);
                }}
        );
        requestBody.put("messages",  messages);
        System.out.println(messages);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send request to the API
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(
                "https://api.openai.com/v1/chat/completions",
                requestEntity,
                Map.class);

        // Process response
        Map<String, Object> responseBody = responseEntity.getBody();
        System.out.println("Response from API: " + responseBody); // Add this line for logging
        List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
        String generatedSummary = "";
        if (!choices.isEmpty()) {
            Map<String, Object> choice = choices.get(0);
            Map<String, Object> message = (Map<String, Object>) choice.get("message");
            generatedSummary = message.get("content").toString();
        }

        return generatedSummary;
    }
    }


