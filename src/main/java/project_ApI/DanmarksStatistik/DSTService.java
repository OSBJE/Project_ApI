package project_ApI.DanmarksStatistik;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import project_ApI.DanmarksStatistik.model.DSTRequestDTO;
import project_ApI.DanmarksStatistik.model.DSTmodel01;
import project_ApI.DanmarksStatistik.model.Variable;
import project_ApI.DanmarksStatistik.repository.DSTrepository;
import project_ApI.chatGPT.model.ChatGPTResponseDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DSTService {

    private final WebClient webClient;

    @Autowired
    private DSTrepository dSTrepository;

    @Autowired
    public DSTService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl("https://api.statbank.dk/v1/data").build();
    }

    public DSTRequestDTO getDST_Data(){

        DSTRequestDTO requestDTO = new DSTRequestDTO();
        requestDTO.setTable("NAHC021");
        requestDTO.setFormat("CSV");


        List<String> formatList = new ArrayList<>();
        formatList.add("CPA");

        List<String>  pricevalueList = new ArrayList<>();
        pricevalueList.add("LAN");

        List<Variable> listVar = new ArrayList<>();

        Variable format = new Variable("FORMAAAL", formatList);
        Variable prisEnhed = new Variable("PRISENHED", pricevalueList);

        List<String> dateList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int var = 2024 - i;
            dateList.add(String.valueOf(var));
        }

        Variable timeSerie = new Variable("Tid", dateList);

        listVar.add(format);
        listVar.add(prisEnhed);
        listVar.add(timeSerie);

        requestDTO.setVariables(listVar);

        return requestDTO;
    }

    public void prompDST(){

        DSTRequestDTO requestDTO = getDST_Data();

        String csvResponse = webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .retrieve()
                .bodyToMono(String.class).block();

        try (BufferedReader reader = new BufferedReader(new StringReader(csvResponse))){
            String header = reader.readLine();

            String line;

            while((line = reader.readLine()) != null){
                DSTmodel01 obj = new DSTmodel01();
                String[] parts = line.split(";");
                if (parts.length == 4){
                    obj.setFormatCode(parts[0]);
                    System.out.println("Split 0 format:    " + parts[0]);

                    String[] indexPrices = parts[1].split(",");
                    obj.setIndexPrice(indexPrices[0]);
                    System.out.println("Split 1  index price:    " + parts[1]);

                    obj.setYear(Integer.parseInt(parts[2]));
                    System.out.println("Split 2  year:    " + parts[2]);

                    obj.setAmount(Integer.parseInt(parts[3]));
                    System.out.println("Split 3:    " + parts[3]);




                }
                dSTrepository.save(obj);
            }

        } catch (IOException e){
            throw  new RuntimeException("Nooo we did not get the CSV data", e);
        }

    }

}
