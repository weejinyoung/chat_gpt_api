package practice.chat_gpt_api.configure;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import practice.chat_gpt_api.properties.ChatGptProperties;
import practice.chat_gpt_api.service.ChatGptService;
import practice.chat_gpt_api.service.DefaultChatGptServiceImpl;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ChatGptProperties.class)
public class ChatGptAutoConfiguration {

    private final ChatGptProperties chatGptProperties;

    @Bean
    @ConditionalOnMissingBean(ChatGptService.class)
    public ChatGptService chatgptService(){
        return new DefaultChatGptServiceImpl(chatGptProperties);
    }

}
