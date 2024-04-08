package com.sergiofah.integracao.view;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sergiofah.integracao.model.Product;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ProductPageController {
	
	private ArrayList<Product> productData; 
	private String selectedLine;
	
	@FXML
	private AnchorPane modelDetailsAnchorPane;
	
	@FXML
	private ComboBox<String> linesComboBox;
	
	@FXML
	private TitledPane modelsTitledPane;
	
	@FXML
	private TreeView<String> modelsTreeView;

	@FXML
	private Label productNameLabel;
	
	@FXML
	private Label productDescLabel;
	
	@FXML
	private ImageView productImageView;
	
	@FXML
	private void OnClickComboBox(ActionEvent evt) {
		selectedLine = linesComboBox.getValue();
		populateTreeView();
		modelsTitledPane.setDisable(false);
		modelsTitledPane.setExpanded(true);
	}
	
    public void populateComboBox(){
    	//gera uma lista de linhas distinta
    	ArrayList<String> lineList = (ArrayList<String>) productData.stream().
    			map(Product::getLine).
    			distinct().
    			collect(Collectors.toList());
    	
    	linesComboBox.setItems(FXCollections.observableArrayList(lineList));
    }
	
	private void populateTreeView() {
		
		//gera uma lista de categorias distinta
    	ArrayList<String> categoryList = (ArrayList<String>) productData.stream().
    			filter(s -> s.getLine()==selectedLine).
    			map(Product::getCategory).
    			distinct().
    			collect(Collectors.toList());
    	
        TreeItem<String> rootItem = new TreeItem<>(selectedLine);
        modelsTreeView.setRoot(rootItem);
		//desabilita a visão do root
    	modelsTreeView.setShowRoot(false);
    	//lista de categorias a ser preenchida
    

    	//preenche a treeView
    	for(String c: categoryList){
    		TreeItem<String> newCategory = new TreeItem<String>(c); 
    		rootItem.getChildren().add(newCategory);
    		for(Product p: productData) {
        		if(p.getCategory()==c) {
        			newCategory.getChildren().add(new TreeItem<String>(p.getModel()));
        		}	
        	}
    	}
    	selectionTreeViewHandler(); 
	}
	//Selecionar o elemento que foi escolhido e chamar a função de preencher os campos
	private void selectionTreeViewHandler(){
        modelsTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if ((newValue != null)&&(newValue.isLeaf())) {
            	String selectedModel = newValue.getValue();
            	
            	Optional<Product> selectedProduct = productData.stream()
                         .filter(p -> p.getModel().equals(selectedModel))
                         .findFirst();
            	populateModelDetails(selectedProduct.get());            
            }
        });
	}
	
	//Preenche os campos com as informações do produto
	public void populateModelDetails(Product p){
		Image loading = new Image(getClass().getResourceAsStream("/images/loading.gif"));
		productNameLabel.setText(p.getModel());
		productDescLabel.setText(p.getDescr());
		//determina como imagem de loading
		productImageView.setImage(loading);
    	modelDetailsAnchorPane.setVisible(true);

	
		//Thread para carregamento da imagem em paralelo
        Thread loadImage = new Thread(() -> {
    		try {
				Image image = new Image(p.getImgUrl());
				productImageView.setImage(image);
			} catch (Exception e) {
				e.printStackTrace();
			}
        });
        
        if(loadImage.isAlive()) {
        	loadImage.interrupt();
        }
        
        loadImage.start();
	}
    
	public void setProductData(ArrayList<Product> productData) {
		this.productData = productData;
		populateComboBox();
	}
	    
}
