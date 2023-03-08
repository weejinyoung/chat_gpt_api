package practice.chat_gpt_api.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import practice.chat_gpt_api.dto.ChatRequest;
import practice.chat_gpt_api.dto.ChatResponse;
import practice.chat_gpt_api.exception.ChatGptException;
import practice.chat_gpt_api.properties.ChatGptProperties;

import java.util.Objects;

@Slf4j
public class DefaultChatGptServiceImpl implements ChatGptService{
    protected final ChatGptProperties chatGptProperties;
    private final String URL = "https://api.openai.com/v1/completions";

    private final String AUTHORIZATION;

    public DefaultChatGptServiceImpl(ChatGptProperties chatGptProperties) {
        this.chatGptProperties = chatGptProperties;
        AUTHORIZATION = "Bearer " + chatGptProperties.getApiKey();
    }

    @Override
    public String sendMessage(String message) {
        ChatRequest chatRequest = new ChatRequest(chatGptProperties.getModel(), message,
                chatGptProperties.getMaxTokens(), chatGptProperties.getTemperature(), chatGptProperties.getTopP());
        log.info("2. this is server's chatRequest toString {}", chatRequest.toString());
        ChatResponse chatResponse = this.getResponse(this.buildHttpEntity(chatRequest));
        log.info("5. this is server's chatResponse toString {}", chatResponse.toString());
        try {
            log.info("6. this is chatResponse.getChoices() toString {}", chatRequest.toString());
            return chatResponse.getChoices().get(0).getText();
        } catch (Exception e) {
            log.error("parse chatgpt message error", e);
            throw e;
        }
    }

    @Override
    public ChatResponse sendChatRequest(ChatRequest chatRequest) {
        return this.getResponse(this.buildHttpEntity(chatRequest));
    }

    public HttpEntity<ChatRequest> buildHttpEntity(ChatRequest chatRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        headers.add("Authorization", AUTHORIZATION);
        return new HttpEntity<>(chatRequest, headers);
    }

    public ChatResponse getResponse(HttpEntity<ChatRequest> chatRequestHttpEntity) {
//        log.info("request url: {}, httpEntity: {}",URL, chatRequestHttpEntity);
        log.info("3. this is server's chatRequestHttpEntity toString {}", chatRequestHttpEntity.toString());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ChatResponse> responseEntity = restTemplate.postForEntity(URL, chatRequestHttpEntity, ChatResponse.class);
        log.info("4. this is server's chatResponseEntity toString {}", Objects.requireNonNull(responseEntity.getBody()).toString());
        if (responseEntity.getStatusCode().isError()) {
            log.error("error response status: {}", responseEntity);
            throw new ChatGptException("error response status :" + responseEntity.getStatusCode().value());
        } else {
//            log.info("response: {}", responseEntity);
        }
        return responseEntity.getBody();
    }
}
