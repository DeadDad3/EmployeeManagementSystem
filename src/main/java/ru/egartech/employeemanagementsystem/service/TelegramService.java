package ru.egartech.employeemanagementsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Service
public class TelegramService {

    private final String botToken = "7001192841:AAG0UWN61pcnKX8IWXURtilZAhrosNwyc08";
    private final String chatId = "856119181";
    private final String apiUrl = "https://api.telegram.org/bot" + botToken + "/sendMessage";

    private final RestTemplate restTemplate;

    public TelegramService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendMessage(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = "{\"chat_id\":\"" + chatId + "\", \"text\":\"" + message + "\"}";

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        restTemplate.postForObject(apiUrl, request, String.class);
    }
}