package com.sergiofah.integracao;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) {
        try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProductPage.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);			
			primaryStage.setTitle("Projeto de Integração - Etapa 04");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
		launch(args);
	}
}