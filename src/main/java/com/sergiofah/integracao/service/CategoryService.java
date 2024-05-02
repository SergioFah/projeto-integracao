package com.sergiofah.integracao.service;

import com.sergiofah.integracao.model.CategoryDTO;

import java.util.List;

public class CategoryService {

    private Service service;
    public CategoryService() {
        this.service = new Service();
    }

    public List<CategoryDTO> getCategoriesFromLineId(Long id) {
        return service.getWebClient()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/categories").queryParam("line","{id}").build(id))
                .retrieve()
                .bodyToFlux(CategoryDTO.class)
                .collectList().block();
    }
}
