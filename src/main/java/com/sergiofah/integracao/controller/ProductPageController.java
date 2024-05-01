package com.sergiofah.integracao.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sergiofah.integracao.model.CategoryDTO;
import com.sergiofah.integracao.model.LineDTO;
import com.sergiofah.integracao.model.ProductDTO;
import com.sergiofah.integracao.service.CategoryService;
import com.sergiofah.integracao.service.LineService;
import com.sergiofah.integracao.service.ProductService;
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

	@FXML
	private Label serverStatus;

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

	private Image loading;

	private Long selectedLineId;

	private List<LineDTO> lineDTOList;
	private List<CategoryDTO> categoryDTOList;
	private List<ProductDTO> productDTOList = new ArrayList<>();

	public ProductPageController() {
        loading = new Image(getClass().getResourceAsStream("/images/loading.gif"));
    }

    @FXML
	private void initialize() {
		Service service = new Service();
		LineService lineService = new LineService();
		if (service.getServerStatus()) {
			this.lineDTOList = lineService.getLines();
			populateComboBox();
		} else {
			serverStatus.setText("Status: Server Offline");
			serverStatus.setTextFill(Color.RED);
		}
	}

	public void populateComboBox() {
		linesComboBox.setItems(FXCollections.observableArrayList(lineDTOList.stream().map(LineDTO::getLine).collect(Collectors.toList())));
	}
	
	@FXML
	private void OnClickComboBox() {
		selectedLineId = lineDTOList.stream()
				.filter(id -> id.getLine().equals(linesComboBox.getValue()))
				.map(LineDTO::getId)
				.findFirst()
				.get();
		populateTreeView();
		modelsTitledPane.setDisable(false);
		modelsTitledPane.setExpanded(true);
	}
	
	private void populateTreeView() {
		CategoryService categoryService = new CategoryService();
		ProductService productService = new ProductService();
    	this.categoryDTOList = categoryService.getCategoriesFromLineId(selectedLineId);
		TreeItem<String> rootItem = new TreeItem<>();
        modelsTreeView.setRoot(rootItem);
        modelsTreeView.setShowRoot(false);

    	for (CategoryDTO c : categoryDTOList) {
			TreeItem<String> newCategory = new TreeItem<>(c.getCategory());
			rootItem.getChildren().add(newCategory);
			List<ProductDTO> productsFromCategory = productService.getProductsFromCategoryId(c.getId());
			this.productDTOList.addAll(productsFromCategory);

			for (ProductDTO p : productsFromCategory) {
				newCategory.getChildren().add(new TreeItem<>(p.getModel()));
			}
			selectionTreeViewHandler();
		}

	}

	private void selectionTreeViewHandler() {
		ProductService productService = new ProductService();
		modelsTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if ((newValue != null) && (newValue.isLeaf())) {
            	String selectedModel = newValue.getValue();
				Long productId = productDTOList.stream().filter(p -> p.getModel().equals(selectedModel)).findFirst().get().getId();
				ProductDTO selectedProductDTO = productService.getProductFromId(productId);
				populateModelDetails(selectedProductDTO);
            }
        });
	}
	
	public void populateModelDetails(ProductDTO p) {
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

        if (loadImage.isAlive()) {
        	loadImage.interrupt();
        }

        loadImage.start();
	}
}
