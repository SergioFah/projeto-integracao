package com.sergiofah.integracao.model;


public class Product {
	private String line; // Linha
	private String category; // Categoria
	private String model; // Modelo
	private String descr; // Texto descritivo
	private String imgUrl; // URL da imagem do produto
	

	public Product(String line, String category, String model, String descr, String imgUrl) {
		this.line = line;
		this.category = category;
		this.model = model;
		this.descr = descr;
		this.imgUrl = imgUrl;
	}

	public String getLine() {
		return line;
	}


	public void setLine(String line) {
		this.line = line;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getDescr() {
		return descr;
	}


	public void setDescr(String descr) {
		this.descr = descr;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
	
}
