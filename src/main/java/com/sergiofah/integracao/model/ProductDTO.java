
package com.sergiofah.integracao.model;

public class ProductDTO {

    private Long id;
    private LineDTO line;
    private CategoryDTO category;
    private String model;
    private String description;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDTO withId(Long id) {
        this.id = id;
        return this;
    }

    public LineDTO getLine() {
        return line;
    }

    public void setLine(LineDTO lineDTO) {
        this.line = lineDTO;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO categoryDTO) {
        this.category = categoryDTO;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
