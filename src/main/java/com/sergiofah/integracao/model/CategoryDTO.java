
package com.sergiofah.integracao.model;

public class CategoryDTO {

    private Long id;
    private LineDTO line;
    private String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryDTO withId(Long id) {
        this.id = id;
        return this;
    }

    public LineDTO getLine() {
        return line;
    }

    public void setLine(LineDTO lineDTO) {
        this.line = lineDTO;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}