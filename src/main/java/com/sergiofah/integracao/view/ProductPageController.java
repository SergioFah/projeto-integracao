package com.sergiofah.integracao.view;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.sergiofah.integracao.model.Product;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class ProductPageController {
	
	private ArrayList<Product> productData;
	private String selectedLine;
	
	
	@FXML
	private ComboBox<String> linesComboBox;
	
	@FXML
	private TitledPane modelsTitledPane;
	
	@FXML
	private TreeView<String> modelsTreeView;
	private TreeItem newNode;
	
	@FXML
	private void OnClickComboBox(ActionEvent evt) {
		selectedLine = linesComboBox.getValue();
		populateTreeView();
		modelsTitledPane.setDisable(false);
		modelsTitledPane.setExpanded(true);
		}
	
	public void populateTreeView() {
		
		//gera uma lista de categorias distinta
    	ArrayList<String> categoryList = (ArrayList<String>) productData.stream().
    			filter(s -> s.getLine()==selectedLine).
    			map(Product::getCategory).
    			distinct().
    			collect(Collectors.toList());
    	
    	//cria o root
        TreeItem<String> rootItem = new TreeItem<>(selectedLine);
        //seta o root
        modelsTreeView.setRoot(rootItem);
		//desabilita a visão do root
    	modelsTreeView.setShowRoot(false);
    	//lista de categorias a ser preenchida
    

    	//preenche a treeView
    	for(String c: categoryList) {
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
	
	public void selectionTreeViewHandler() {
        modelsTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if ((newValue != null)&&(newValue.isLeaf())) {
                System.out.println("Item selecionado: " + newValue.getValue());
            }
        });
	}
	
    public void populateComboBox() {
    	//gera uma lista de linhas distinta
    	ArrayList<String> lineList = (ArrayList<String>) productData.
    			stream().
    			map(Product::getLine).
    			distinct().
    			collect(Collectors.toList());
    	
    	linesComboBox.setItems(FXCollections.observableArrayList(lineList));
    }
    
	public void setProductData(ArrayList<Product> productData) {
		this.productData = productData;
		populateComboBox();
	}
	    
}
