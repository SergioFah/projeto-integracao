package com.sergiofah.integracao;

import java.io.IOException;
import java.util.ArrayList;

import com.sergiofah.integracao.model.Product;
import com.sergiofah.integracao.view.ProductPageController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	private ArrayList<Product> productData = new ArrayList<Product>();
	
	
	public MainApp() {
		//Adiciona alguns exemplos
		productData.add(new Product("Cronos","Cronos Old", "Cronos 6001‑A", "Este modelo monofásico atende as necessidades das concessionárias de energia elétrica. É um medidor compacto com dimensões compatíveis as instalações e equipamentos de calibração já existentes. Fabricado com materiais de alta tecnologia e modernas técnicas de produção, o medidor eletrônico Cronos 6001-A, é uma solução totalmente nacional para medição de elétrica, com preço extremamente competitivo. Confira as vantagens!", "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/CRONOS-6001-A-2.png"));
		
		productData.add(new Product("Cronos","Cronos New", "Cronos 6001‑A", "Este modelo monofásico atende as necessidades das concessionárias de energia elétrica. É um medidor compacto com dimensões compatíveis as instalações e equipamentos de calibração já existentes. Fabricado com materiais de alta tecnologia e modernas técnicas de produção, o medidor eletrônico Cronos 6001-A, é uma solução totalmente nacional para medição de elétrica, com preço extremamente competitivo. Confira as vantagens!", "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/CRONOS-6001-A-2.png"));

		productData.add(new Product("Ares","Ares TB", "ARES 7021", "O medidor ARES 7021 auxilia as concessionárias de energia a obter informações referentes ao consumo de energia de seus clientes. Projetado para operar em redes monofásicas a dois fios, registrar o consumo de energia ativa e reativa nos quatro quadrantes e atender à regulamentação normativa da ANEEL quanto a Tarifa Branca", "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/ARES-7021.png"));
		productData.add(new Product("Ares","Ares TB", "ARES 7021", "O medidor ARES 7021 auxilia as concessionárias de energia a obter informações referentes ao consumo de energia de seus clientes. Projetado para operar em redes monofásicas a dois fios, registrar o consumo de energia ativa e reativa nos quatro quadrantes e atender à regulamentação normativa da ANEEL quanto a Tarifa Branca", "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/ARES-7021.png"));
		productData.add(new Product("Ares","Ares TB", "ARES 7021", "O medidor ARES 7021 auxilia as concessionárias de energia a obter informações referentes ao consumo de energia de seus clientes. Projetado para operar em redes monofásicas a dois fios, registrar o consumo de energia ativa e reativa nos quatro quadrantes e atender à regulamentação normativa da ANEEL quanto a Tarifa Branca", "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/ARES-7021.png"));
		productData.add(new Product("Ares","Ares TB", "ARES 7021", "O medidor ARES 7021 auxilia as concessionárias de energia a obter informações referentes ao consumo de energia de seus clientes. Projetado para operar em redes monofásicas a dois fios, registrar o consumo de energia ativa e reativa nos quatro quadrantes e atender à regulamentação normativa da ANEEL quanto a Tarifa Branca", "https://www.eletraenergy.com.br/wp-content/uploads/2022/09/ARES-7021.png"));

	}
	
	@Override
	public void start(Stage primaryStage) {
        try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("view/ProductPage.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			ProductPageController controller = loader.getController();
			controller.setProductData(productData);			
			
			primaryStage.setTitle("Projeto de Integração - Etapa 01");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}


