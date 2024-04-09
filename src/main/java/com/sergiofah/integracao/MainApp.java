package com.sergiofah.integracao;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	@Override
	public void start(Stage primaryStage) {
        try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProductPage.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);			
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


