package practice.chat_gpt_api.configure;


import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import practice.chat_gpt_api.properties.ChatGptProperties;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ChatGptProperties.class)
public class ChatConfiguration {

    private final ChatGptProperties chatGptProperties;


    @Bean
    public OpenAiService openAiApi() {
        return new OpenAiService(chatGptProperties.getApiKey());
    }

}
