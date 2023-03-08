package practice.chat_gpt_api.service;


public interface ChatService {
    void sendMessage(String message);
    String receiveMessage(String message);

}