package com.maddytec.ai.http.controller;

import com.maddytec.ai.http.request.QuestionRequest;
import com.maddytec.ai.http.response.LlamaResponse;
import com.maddytec.ai.service.LlamaAiServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ai")
public class LlamaRestController {

    private final LlamaAiServiceImpl llamaAiService;

    @PostMapping("/ask")
    public LlamaResponse askAi(@RequestBody QuestionRequest questionRequest) {
        return llamaAiService.askAi(questionRequest);
    }

}
