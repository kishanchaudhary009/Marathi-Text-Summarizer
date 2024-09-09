package com.example.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Summarymodelservice {

    private static final String API_URL = "https://api-inference.huggingface.co/models/kishanvvvv/marathi_summary1";
    private static final String TOKEN = "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";

    public String giveSummary(String inputText) {
        // Construct payload using a Map
        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("inputs", inputText);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("min_length", 130);
        parameters.put("max_length", 200);
        parameters.put("num_beams", 7);
        parameters.put("early_stopping", true);
        payloadMap.put("parameters", parameters);

        // Convert payload map to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String payload;
        try {
            payload = objectMapper.writeValueAsString(payloadMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Failed to construct payload";
        }

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(TOKEN);

        // Create request entity
        HttpEntity<String> requestEntity = new HttpEntity<>(payload, headers);

        // Send POST request
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(API_URL, requestEntity, String.class);

     // Parse response to extract summary
        String response = responseEntity.getBody();
        if (response != null) {
            try {
                JsonNode root = objectMapper.readTree(response);
                if (root.isArray() && root.size() > 0) {
                    JsonNode firstElement = root.get(0);
                    JsonNode generatedTextNode = firstElement.get("generated_text");
                    if (generatedTextNode != null) {
                        return generatedTextNode.asText();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Failed to parse response";
            }
        }
        return "No summary available";
    }
}
