package practice.chat_gpt_api.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "chatgpt")
public class ChatGptProperties {

    //apiKey
    private String apiKey = "";
    private String model = "text-davinci-003";
    private Integer maxTokens = 300;
    private Double temperature = 0.0;
    private Double topP = 1.0;
}
