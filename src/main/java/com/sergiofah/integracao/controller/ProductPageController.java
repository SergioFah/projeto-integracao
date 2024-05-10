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
	public Label serverStatus;

	@FXML
	public AnchorPane modelDetailsAnchorPane;

	@FXML
	public ComboBox<String> linesComboBox;

	@FXML
	public TitledPane modelsTitledPane;

	@FXML
	public TreeView<String> modelsTreeView;

	@FXML
	public Label productNameLabel;

	@FXML
	public Label productDescLabel;

	@FXML
	public ImageView productImageView;

	private Image loading = new Image(getClass().getResourceAsStream("/images/loading.gif"));;
	public Long selectedLineId;
	public List<LineDTO> lineDTOList;
	public List<CategoryDTO> categoryDTOList;
	public List<ProductDTO> productDTOList;
	public Service service;
	public LineService lineService;
	public CategoryService categoryService;
	public ProductService productService;
	public Thread loadImage;

	public ProductPageController() {
		productDTOList = new ArrayList<>();
		service = new Service();
		lineService = new LineService();
		categoryService = new CategoryService();
		productService = new ProductService();
	}

	@FXML
	public void initialize() {
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
		linesComboBox.valueProperty().addListener(((observable, oldValue, newValue) -> onClickComboBox(newValue)));
	}

	public void onClickComboBox(String selectedLine) {
		selectedLineId = lineDTOList.stream()
				.filter(id -> id.getLine().equals(selectedLine))
				.map(LineDTO::getId)
				.findFirst()
				.get();

		populateTreeView();
		modelsTitledPane.setDisable(false);
		modelsTitledPane.setExpanded(true);
	}

	public void populateTreeView() {
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
		}
		modelsTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectionTreeViewHandler(newValue));
	}

	public void selectionTreeViewHandler(TreeItem<String> newValue) {
		if ((newValue != null) && (newValue.isLeaf())) {
			Long productId = productDTOList.stream().filter(p -> p.getModel().equals(newValue.getValue())).findFirst().get().getId();
			ProductDTO selectedProductDTO = productService.getProductFromId(productId);
			populateModelDetails(selectedProductDTO);
		}
	}

	public void populateModelDetails(ProductDTO p) {
		productNameLabel.setText(p.getModel());
		productDescLabel.setText(p.getDescription());
		productImageView.setImage(loading);
		modelDetailsAnchorPane.setVisible(true);

		loadImage = new Thread(() -> {
			try {
				productImageView.setImage(new Image(p.getImageUrl()));
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
