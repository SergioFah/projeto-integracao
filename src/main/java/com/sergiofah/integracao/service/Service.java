package com.sergiofah.integracao.service;

import com.sergiofah.integracao.model.Category;
import com.sergiofah.integracao.model.Line;
import com.sergiofah.integracao.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URL;
import java.util.List;
import java.util.Optional;

public class Service {
    private WebClient webClient;
    public Service() {
        this.webClient = WebClient
                .builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    public boolean getServerStatus(){
        try {
            webClient
                    .get()
                    .uri("/lines")
                    .retrieve().bodyToMono(String.class).block();
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    public List<Line> getLines() {
        return webClient
                .get()
                .uri("/lines")
                .retrieve()
                .bodyToFlux(Line.class)
                .collectList().block();
    }
    public List<Category> getCategoriesFromLineId(Long id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/categories").queryParam("line","{id}").build(id))
                .retrieve()
                .bodyToFlux(Category.class)
                .collectList().block();
    }

    public List<Product> getProductsFromCategoryId(Long id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/products").queryParam("category","{id}").build(id))
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList().block();
    }

    public Product getProductFromId(Long id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/products/{id}").build(id))
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }
}
