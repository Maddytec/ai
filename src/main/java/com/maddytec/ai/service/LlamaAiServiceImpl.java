package com.maddytec.ai.service;

import com.maddytec.ai.client.OllamaChatClient;
import com.maddytec.ai.http.request.QuestionRequest;
import com.maddytec.ai.http.response.LlamaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LlamaAiServiceImpl implements LlamaAiService {

    private final OllamaChatClient chatClient;

    @Value("${ai.ollama.rule}")
    private String rule;

    @Override
    public LlamaResponse askAi(QuestionRequest questionRequest) {
        String prompt = String.format(rule, questionRequest.getQuestion());
        final String llamaMessage = chatClient.askAi(prompt);
        return new LlamaResponse(llamaMessage);
    }

}