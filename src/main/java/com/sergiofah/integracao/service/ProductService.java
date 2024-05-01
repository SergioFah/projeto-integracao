package com.sergiofah.integracao.service;

import com.sergiofah.integracao.model.ProductDTO;

import java.util.List;

public class ProductService {
    public List<ProductDTO> getProductsFromCategoryId(Long id) {
        Service service = new Service();
        return service.getWebClient()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/products").queryParam("category","{id}").build(id))
                .retrieve()
                .bodyToFlux(ProductDTO.class)
                .collectList().block();
    }

    public ProductDTO getProductFromId(Long id) {
        Service service = new Service();
        return service.getWebClient()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/products/{id}").build(id))
                .retrieve()
                .bodyToMono(ProductDTO.class)
                .block();
    }

}
