package project_ApI.chatGPT;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ChatGPTController {

    @Autowired
    ChatGPTService chatGPTService;

    @Value("")
    private String openAI_key;

    @GetMapping("/key")
    public String key(){
        return openAI_key;
    }

    @GetMapping("/test")
    public Map<String, Object> test(){
        Map<String, Object> testmap = chatGPTService.prompChatty();
    }

}
