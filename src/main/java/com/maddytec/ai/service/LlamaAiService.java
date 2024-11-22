package com.maddytec.ai.service;

import com.maddytec.ai.http.request.QuestionRequest;
import com.maddytec.ai.http.response.LlamaResponse;

public interface LlamaAiService {

  LlamaResponse askAi(QuestionRequest questionRequest);
}
