package com.example.msswquiz.service;

import com.example.msswquiz.model.Question;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StarWarsApiService {

    private static final String STAR_WARS_API_BASE_URL = "https://swapi.dev/api/";

    @Autowired
    private RestTemplate restTemplate;
                            //Pergunta vinda do banco
    public Question fillQuestion(Question question) {
        // Obter o tipo e característica da pergunta
        String type = question.getType();
        String characteristic = question.getCharacteristic();

        // Construir a URL da API com base no tipo da pergunta
        String apiUrl = STAR_WARS_API_BASE_URL + type + "/";

        // Fazer uma solicitação à API e obter a resposta como uma String
        String response = restTemplate.getForObject(apiUrl, String.class);

        // Lógica para extrair o valor específico baseado na característica da resposta da API
        String apiData = extractApiData(response, characteristic);

        // Preencher a pergunta com o valor extraído, substituindo "[dado da API]" pelo valor real
        question.setDescription(question.getDescription().replace("[dado da API]", apiData));

        // Retornar a pergunta preenchida
        return question;
    }

    private String extractApiData(String apiResponse, String characteristic) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(apiResponse);

            // Verifique se a característica existe no JSON
            if (jsonNode.has(characteristic)) {
                JsonNode characteristicNode = jsonNode.get(characteristic);
                return characteristicNode.asText();
            } else {
                throw new RuntimeException("Característica não encontrada na resposta da API");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar dados da API", e);
        }
    }

    }
