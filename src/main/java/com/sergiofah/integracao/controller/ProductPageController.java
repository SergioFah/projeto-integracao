package com.sergiofah.integracao.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sergiofah.controller.ProductController;
import com.sergiofah.model.Category;
import com.sergiofah.model.Line;
import com.sergiofah.model.Product;
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
	
	private String selectedLine;

	Image loading = new Image(getClass().getResourceAsStream("/images/loading.gif"));

	ProductController productController = new ProductController();

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
	private void initialize() {
		populateComboBox();
	}

	public void populateComboBox(){
		linesComboBox.setItems(FXCollections.observableArrayList(productController.getLines()));
	}
	
	@FXML
	private void OnClickComboBox(ActionEvent evt) {
		selectedLine = linesComboBox.getValue();
		populateTreeView();
		modelsTitledPane.setDisable(false);
		modelsTitledPane.setExpanded(true);
	}
	
	private void populateTreeView() {
		
    	List<Category> categoryList = productController.getCategories();
    	
        TreeItem<String> rootItem = new TreeItem<>(selectedLine);
        modelsTreeView.setRoot(rootItem);
        modelsTreeView.setShowRoot(false);
    
    	for(Category c: categoryList) {
			if (c.getLine().equals(selectedLine)) {
				TreeItem<String> newCategory = new TreeItem<>(c.getCategory());
				rootItem.getChildren().add(newCategory);
				List<String> productsFromCategory = productController.getProductsFromCategory(c.getCategory());
				for (String p : productsFromCategory) {
					newCategory.getChildren().add(new TreeItem<String>(p));
				}
			}
			selectionTreeViewHandler();
		}
	}
	private void selectionTreeViewHandler(){
        modelsTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if ((newValue != null) && (newValue.isLeaf())) {
            	String selectedModel = newValue.getValue();
            	Optional<Product> selectedProduct = productController.getProductFromModel(selectedModel);

				populateModelDetails(selectedProduct.get());
            }
        });
	}
	
	public void populateModelDetails(Product p){
		productNameLabel.setText(p.getModel());
		productDescLabel.setText(p.getDescr());
		productImageView.setImage(loading);
    	modelDetailsAnchorPane.setVisible(true);


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
}
