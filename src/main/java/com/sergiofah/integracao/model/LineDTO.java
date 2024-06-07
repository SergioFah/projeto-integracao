
package com.sergiofah.integracao.model;

public class LineDTO {

    private Long id;
    private String line;

    public LineDTO(Long id, String line) {
        this.id = id;
        this.line = line;
    }

    public LineDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}