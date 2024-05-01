package com.sergiofah.integracao.service;

import com.sergiofah.integracao.model.LineDTO;

import java.util.List;

public class LineService {

    private Service service;

    public LineService() {
        this.service = new Service();
    }

    public List<LineDTO> getLines() {
        return service.getWebClient()
                .get()
                .uri("/lines")
                .retrieve()
                .bodyToFlux(LineDTO.class)
                .collectList().block();
    }
}
