package practice.chat_gpt_api.service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatGptService implements ChatService {

    private final OpenAiService openAiService;

    public String chatWithChatGpt(String message) {
        ChatMessage chatMessage = new ChatMessage("user", message);
        List<ChatMessage> chatMessageList = new ArrayList<>();
        chatMessageList.add(chatMessage);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .messages(chatMessageList)
                .model("gpt-3.5-turbo")
                .build();

        ChatCompletionResult chatCompletion = openAiService.createChatCompletion(chatCompletionRequest);
        return chatCompletion.getChoices().get(0).getMessage().getContent();
    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public String receiveMessage(String message) {
        return null;
    }
}
