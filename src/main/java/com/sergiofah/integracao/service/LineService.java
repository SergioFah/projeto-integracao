package com.sergiofah.integracao.service;

import com.sergiofah.integracao.model.LineDTO;

import java.util.List;

public class LineService {
    public List<LineDTO> getLines() {
        Service service = new Service();
        return service.getWebClient()
                .get()
                .uri("/lines")
                .retrieve()
                .bodyToFlux(LineDTO.class)
                .collectList().block();
    }
}
