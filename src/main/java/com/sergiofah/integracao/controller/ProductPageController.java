package com.sergiofah.integracao.controller;

import java.util.List;
import java.util.Optional;

import com.sergiofah.controller.ProductDAO;
import com.sergiofah.model.Category;
import com.sergiofah.model.Product;
import javafx.collections.FXCollections;
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

	private ProductDAO productDAO;

	Image loading;

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

    public ProductPageController() {
        loading = new Image(getClass().getResourceAsStream("/images/loading.gif"));
    }

    @FXML
	private void initialize() {
		productDAO = new ProductDAO();
		populateComboBox();
	}

	public void populateComboBox(){
		linesComboBox.setItems(FXCollections.observableArrayList(productDAO.getLines()));
	}
	
	@FXML
	private void OnClickComboBox() {
		selectedLine = linesComboBox.getValue();
		populateTreeView();
		modelsTitledPane.setDisable(false);
		modelsTitledPane.setExpanded(true);
	}
	
	private void populateTreeView() {
		
    	List<Category> categoryList = productDAO.getCategoriesFromLine(selectedLine);

        TreeItem<String> rootItem = new TreeItem<>(selectedLine);
        modelsTreeView.setRoot(rootItem);
        modelsTreeView.setShowRoot(false);
    
    	for(Category c: categoryList) {
			TreeItem<String> newCategory = new TreeItem<>(c.getCategory());
			rootItem.getChildren().add(newCategory);
			List<Product> productsFromCategory = productDAO.getProductListFromCategory(c);
			for (Product p : productsFromCategory) {
				newCategory.getChildren().add(new TreeItem<>(p.getModel()));
			}
			selectionTreeViewHandler();
		}
	}
	private void selectionTreeViewHandler(){
        modelsTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if ((newValue != null) && (newValue.isLeaf())) {
            	String selectedModel = newValue.getValue();
            	Optional<Product> selectedProduct = productDAO.getProductFromModel(selectedModel);
				populateModelDetails(selectedProduct.get());
            }
        });
	}
	
	public void populateModelDetails(Product p){
		productNameLabel.setText(p.getModel());
		productDescLabel.setText(p.getDescription());
		productImageView.setImage(loading);
		modelDetailsAnchorPane.setVisible(true);


        Thread loadImage = new Thread(() -> {
    		try {
				Image image = new Image(p.getImageUrl());
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
