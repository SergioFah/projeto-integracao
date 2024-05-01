package com.sergiofah.integracao.service;

import com.sergiofah.integracao.model.CategoryDTO;

import java.util.List;

public class CategoryService {
    public List<CategoryDTO> getCategoriesFromLineId(Long id) {

        Service service = new Service();

        return service.getWebClient()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/categories").queryParam("line","{id}").build(id))
                .retrieve()
                .bodyToFlux(CategoryDTO.class)
                .collectList().block();
    }
}
