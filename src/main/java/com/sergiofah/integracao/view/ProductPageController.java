package com.sergiofah.integracao.view;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.sergiofah.integracao.model.Product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class ProductPageController {
	
	private ArrayList<Product> productData;
	
	@FXML
	private ComboBox<String> linesComboBox;
	
	
    public void populateComboBox() {
    	ArrayList<String> lineList = (ArrayList<String>) productData.stream().map(Product::getLine).distinct().collect(Collectors.toList());
    	linesComboBox.setItems(FXCollections.observableArrayList(lineList));
    }


	public void setProductData(ArrayList<Product> productData) {
		this.productData = productData;
		populateComboBox();
	}
	    
}
