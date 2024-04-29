package com.sergiofah.integracao.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sergiofah.integracao.model.Category;
import com.sergiofah.integracao.model.Line;
import com.sergiofah.integracao.model.Product;
import com.sergiofah.integracao.service.Service;
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
import javafx.scene.paint.Color;


public class ProductPageController {

	private Long selectedLineId;

	private Service service;

	@FXML
	private Label serverStatus;

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
	private List<Line> lineList;
	private List<Category> categoryList;
	private List<Product> productList = new ArrayList<>();

	public ProductPageController() {
        loading = new Image(getClass().getResourceAsStream("/images/loading.gif"));
    }

    @FXML
	private void initialize() {
		this.service = new Service();
		if(service.getServerStatus()) {
			this.lineList = service.getLines();
			populateComboBox();
		}else{
			serverStatus.setText("Status: Server Offline");
			serverStatus.setTextFill(Color.RED);
		}
	}

	public void populateComboBox(){
		linesComboBox.setItems(FXCollections.observableArrayList(lineList.stream().map(Line::getLine).collect(Collectors.toList())));
	}
	
	@FXML
	private void OnClickComboBox() {
		selectedLineId = lineList.stream()
				.filter(id -> id.getLine().equals(linesComboBox.getValue()))
				.map(Line::getId)
				.findFirst()
				.get();
		populateTreeView();
		modelsTitledPane.setDisable(false);
		modelsTitledPane.setExpanded(true);
	}
	
	private void populateTreeView() {

    	this.categoryList = service.getCategoriesFromLineId(selectedLineId);
		TreeItem<String> rootItem = new TreeItem<>();
        modelsTreeView.setRoot(rootItem);
        modelsTreeView.setShowRoot(false);

    	for(Category c: categoryList) {
			TreeItem<String> newCategory = new TreeItem<>(c.getCategory());
			rootItem.getChildren().add(newCategory);
			List<Product> productsFromCategory = service.getProductsFromCategoryId(c.getId());
			this.productList.addAll(productsFromCategory);

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
				Long productId = productList.stream().filter(p -> p.getModel().equals(selectedModel)).findFirst().get().getId();
				Product selectedProduct = service.getProductFromId(productId);
				populateModelDetails(selectedProduct);
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
