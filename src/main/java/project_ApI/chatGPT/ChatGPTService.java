package project_ApI.chatGPT;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import project_ApI.chatGPT.model.*;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatGPTService {

    private final WebClient webClient;

    @Value("${openai.api.key}")
    private String apiKey;

            //= System.getenv("openai.api.key");

    @Autowired
    public ChatGPTService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1/chat/completions").build();
    }


    public ChatGPTRequestDTO messageChatGPT(){

        ChatGPTRequestDTO requestDTO = new ChatGPTRequestDTO();
        requestDTO.setModel("gpt-3.5-turbo");
        requestDTO.setTemperature(1.0);
        requestDTO.setMaxTokens(20);
        requestDTO.setN(3);
        requestDTO.setStream(false);
        requestDTO.setPresencePenalty(1.0);

        List<Message> listmsg = new ArrayList<>();
        listmsg.add(new Message("system", "You are a god!"));
        listmsg.add(new Message("user", "Give me the sickes quot from the bible"));
        requestDTO.setMessages(listmsg);

        System.out.println(apiKey);

        return requestDTO;
    }

    public Map<String, Object> prompChatGPT(){

        ChatGPTRequestDTO requestDTO = messageChatGPT();

        ChatGPTResponseDTO responseDTO = webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .headers(h -> h.setBearerAuth(apiKey))
                .bodyValue(requestDTO)
                .retrieve()
                .bodyToMono(ChatGPTResponseDTO.class).block();

        List<Choice> lst = responseDTO.getChoices();
        Usage usg = responseDTO.getUsage();

        Map<String, Object> map = new HashMap<>();
        map.put("Usage", usg);
        map.put("Choices", lst);

        return map;

    }


}
