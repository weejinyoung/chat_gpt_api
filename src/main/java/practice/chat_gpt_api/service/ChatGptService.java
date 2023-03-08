package practice.chat_gpt_api.service;


import practice.chat_gpt_api.dto.ChatRequest;
import practice.chat_gpt_api.dto.ChatResponse;

public interface ChatGptService {
    String sendMessage(String message);
    ChatResponse sendChatRequest(ChatRequest request);

}