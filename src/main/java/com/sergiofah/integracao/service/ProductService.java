package com.sergiofah.integracao.service;

import com.sergiofah.integracao.model.ProductDTO;

import java.util.List;

public class ProductService {

    private Service service;
    public ProductService() {
        this.service = new Service();
    }

    public List<ProductDTO> getProductsFromCategoryId(Long id) {
        return service.getWebClient()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/products").queryParam("category","{id}").build(id))
                .retrieve()
                .bodyToFlux(ProductDTO.class)
                .collectList().block();
    }

    public ProductDTO getProductFromId(Long id) {
        return service.getWebClient()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/products/{id}").build(id))
                .retrieve()
                .bodyToMono(ProductDTO.class)
                .block();
    }

}
