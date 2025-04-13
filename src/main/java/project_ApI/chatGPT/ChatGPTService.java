package project_ApI.chatGPT;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ChatGPTService {

    private final WebClient webClient;

    private static final String apiKey = System.getenv("openai.api.key");

    @Autowired
    public ChatGPTService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1/chat/completions").build();
    }




}
