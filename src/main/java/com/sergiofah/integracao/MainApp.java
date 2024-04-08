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
		
		//Adição de elementos na lista para exemplo
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


