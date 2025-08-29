package com.example.javaqualifier.service;

import com.example.javaqualifier.model.WebhookResponse;
import com.example.javaqualifier.util.SqlSolutions;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class QualifierService {

    private static final String INIT_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

    private static final String NAME = "Ayush Sengupta";
    private static final String REG_NO = "22BAI1032";
    private static final String EMAIL = "ayush.sengupta2022@vitstudent.ac.in";

    private final RestTemplate restTemplate = new RestTemplate();

    public void executeWorkflow() {
        try {

            Map<String, String> body = new HashMap<>();
            body.put("name", NAME);
            body.put("regNo", REG_NO);
            body.put("email", EMAIL);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

            ResponseEntity<WebhookResponse> response = restTemplate.postForEntity(
                    INIT_URL, request, WebhookResponse.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                WebhookResponse webhookResponse = response.getBody();

                String webhookUrl = webhookResponse.getWebhook();
                String accessToken = webhookResponse.getAccessToken();

                int lastTwoDigits = Integer.parseInt(REG_NO.substring(REG_NO.length() - 2));
                String finalQuery = SqlSolutions.getEvenSolution();

                submitSolution(webhookUrl, accessToken, finalQuery);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void submitSolution(String webhookUrl, String accessToken, String finalQuery) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", accessToken);


            Map<String, String> body = new HashMap<>();
            body.put("finalQuery", finalQuery);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    webhookUrl, request, String.class);

            System.out.println("Submission Response: " + response.getStatusCode() 
                           + " - " + response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
