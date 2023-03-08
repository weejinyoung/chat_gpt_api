package practice.chat_gpt_api.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import practice.chat_gpt_api.service.ChatGptService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ChatGptController {

    private final ChatGptService chatgptService;

    @GetMapping("/talk")
    public ResponseEntity<String> talkWithChatGpt(@RequestParam(value = "message") String message) {
        log.info("1. this is user's request message {}", message);
        String responseMessage = chatgptService.sendMessage(message);
        log.info("7. this is server's response message {}", message);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

}
