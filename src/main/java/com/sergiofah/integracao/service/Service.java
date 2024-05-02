package com.sergiofah.integracao.service;

import org.springframework.web.reactive.function.client.WebClient;

public class Service {

    private WebClient webClient;

    public Service() {
        this.webClient = WebClient
                .builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    public WebClient getWebClient() {
        return webClient;
    }

    public boolean getServerStatus(){
        try {
            webClient
                    .get()
                    .uri("/lines")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
