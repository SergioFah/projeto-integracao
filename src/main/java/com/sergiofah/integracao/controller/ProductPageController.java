package com.sergiofah.integracao.controller;

import java.util.ArrayList;
import java.util.List;
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
	
	private List<Product> productData = new ArrayList<>();
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
	private void initialize() {
		String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas accumsan, quam accumsan aliquam dignissim, libero sapien varius eros, at luctus dolor massa quis justo.";
		productData.add(new Product("Cronos","Cronos Old", "Cronos 6001‑A", "Este modelo monofásico atende as necessidades das concessionárias de energia elétrica. É um medidor compacto com dimensões compatíveis as instalações e equipamentos de calibração já existentes. Fabricado com materiais de alta tecnologia e modernas técnicas de produção, o medidor eletrônico Cronos 6001-A, é uma solução totalmente nacional para medição de elétrica, com preço extremamente competitivo.", "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/CRONOS-6001-A-2.png"));
		productData.add(new Product("Cronos", "Cronos Old", "Cronos 6003", "Com a mais moderna tecnologia disponível no mercado, os medidores da linha Cronos polifásica oferecem a conhecida confiabilidade dos produtos Eletra. Uma solução efetiva de custo para aplicação em consumidores residenciais com ligações em sistemas estrela a quatro fios (medição trifásica) ou três fios (medição bifásica). Foram desenvolvidos para fornecer segurança com estrutura mecânica de alta resistência e com as opções de dispositivos antifraude.", "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/Cronos-6003.png"));
		productData.add(new Product("Cronos", "Cronos Old", "Cronos 7023", "O Cronos 7023 é um medidor eletrônico de energia ativa e reativa, apropriado para realizar medições em sistemas estrela a quatro fios (medição trifásica) ou três fios (medição bifásica). Este medidor conta com um mostrador digital em LCD e foi desenvolvido com uma estrutura mecânica de alta resistência e recursos que ajudam a prevenir fraudes, reduzindo as perdas comerciais das distribuidoras de energia.", "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/Cronos-7023.png"));
		productData.add(new Product("Cronos", "Cronos L", "Cronos 6021L", "Os medidores Cronos 6021L são compactos com dimensões compatíveis às instalações e equipamentos de calibração já existentes. Fabricados com materiais de alta tecnologia e modernas técnicas de produção, os medidores eletrônicos Cronos formam uma solução totalmente nacional para medição de energia ativa em consumidores residenciais.", "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/CRONOS-6021L-862x1024.png"));
		productData.add(new Product("Cronos", "Cronos L", "Cronos 7023L", "Os medidores Cronos 7023L são compactos com dimensões compatíveis às instalações e equipamentos de calibração já existentes. Fabricados com materiais de alta tecnologia e modernas técnicas de produção, os medidores eletrônicos Cronos formam uma solução totalmente nacional para medição de energia ativa em consumidores residenciais.", "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/CRONOS-7023L.png"));
		productData.add(new Product("Cronos", "Cronos NG", "Cronos 6001‑NG", loremIpsum, "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/CRONOS-6001-A-2.png"));
		productData.add(new Product("Cronos", "Cronos NG", "Cronos 6003‑NG", loremIpsum, "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/Cronos-6003.png"));
		productData.add(new Product("Cronos", "Cronos NG", "Cronos 6021‑NG", loremIpsum, "https://images.tcdn.com.br/img/img_prod/1166724/medidor_monofasico_eletra_cronos_6021_ng_1el_2fios_100a_115_240v_135_1_2e1fbc6b3249a62e2cde20c0687e6a59.jpg"));
		productData.add(new Product("Cronos", "Cronos NG", "Cronos 6031‑NG", loremIpsum, "https://images.tcdn.com.br/img/img_prod/1166724/medidor_monofasico_eletra_cronos_6031ng_1el_3fios_100a_115_240v_127_1_0886e4f60e6e2313519dd3e2ad249a3c.jpg"));
		productData.add(new Product("Cronos", "Cronos NG", "Cronos 7021‑NG", loremIpsum, "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/ARES-7021.png"));
		productData.add(new Product("Cronos", "Cronos NG", "Cronos 7023‑NG", loremIpsum, "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/ARES-7021.png"));
		productData.add(new Product("Ares", "Ares TB", "ARES 7021", "O medidor ARES 7021 auxilia as concessionárias de energia a obter informações referentes ao consumo de energia de seus clientes. Projetado para operar em redes monofásicas a dois fios, registrar o consumo de energia ativa e reativa nos quatro quadrantes e atender à regulamentação normativa da ANEEL quanto a Tarifa Branca", "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/ARES-7021.png"));
		productData.add(new Product("Ares", "Ares TB", "ARES 7031", loremIpsum, "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/ARES-7031.png"));
		productData.add(new Product("Ares", "Ares TB", "ARES 7023", loremIpsum, "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/Cronos-7023.png"));
		productData.add(new Product("Ares", "Ares TB", "ARES 7021", loremIpsum, "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/ARES-7021.png"));		
	
		populateComboBox();
	
	}
	
	
	@FXML
	private void OnClickComboBox(ActionEvent evt) {
		selectedLine = linesComboBox.getValue();
		populateTreeView();
		modelsTitledPane.setDisable(false);
		modelsTitledPane.setExpanded(true);
	}
	
    public void populateComboBox(){
    	List<String> lineList = productData.stream().
    			map(Product::getLine).
    			distinct().
    			collect(Collectors.toList());
    	
    	linesComboBox.setItems(FXCollections.observableArrayList(lineList));
    }
	
	private void populateTreeView() {
		
    	List<String> categoryList = productData.stream().
    			filter(s -> s.getLine().equals(selectedLine)).
    			map(Product::getCategory).
    			distinct().
    			collect(Collectors.toList());
    	
        TreeItem<String> rootItem = new TreeItem<>(selectedLine);
        modelsTreeView.setRoot(rootItem);
        modelsTreeView.setShowRoot(false);
    
    	for(String c: categoryList){
    		TreeItem<String> newCategory = new TreeItem<>(c); 
    		rootItem.getChildren().add(newCategory);
    		for(Product p: productData) {
        		if(p.getCategory().equals(c)) {
        			newCategory.getChildren().add(new TreeItem<String>(p.getModel()));
        		}	
        	}
    	}
    	selectionTreeViewHandler(); 
	}
	private void selectionTreeViewHandler(){
        modelsTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if ((newValue != null) && (newValue.isLeaf())) {
            	String selectedModel = newValue.getValue();
            	
            	Optional<Product> selectedProduct = productData.stream()
                         .filter(p -> p.getModel().equals(selectedModel))
                         .findFirst();
            	populateModelDetails(selectedProduct.get());            
            }
        });
	}
	
	public void populateModelDetails(Product p){
		Image loading = new Image(getClass().getResourceAsStream("/images/loading.gif"));
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
