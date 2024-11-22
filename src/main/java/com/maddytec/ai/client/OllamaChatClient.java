package com.maddytec.ai.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Component
public class OllamaChatClient implements ChatClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String model;

    public OllamaChatClient(
            RestTemplate restTemplate,
            @Value("${spring.ai.ollama.base-url}") String baseUrl,
            @Value("${spring.ai.ollama.model}") String model) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.model = model;
    }

    @Override
    public String askAi(String prompt) {
        String url = String.format("%s/generate", baseUrl);
        Map<String, Object> request = new HashMap<>();
        request.put("model", model);
        request.put("prompt", prompt);
        request.put("stream", false);

        try {
            String response = restTemplate.postForObject(url, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);
            return root.get("response").asText();
        } catch (HttpClientErrorException e) {
            log.error("Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
            return "We were unable to process your query";
        }
    }

}
