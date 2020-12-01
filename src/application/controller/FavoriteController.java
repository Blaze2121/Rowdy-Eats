package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.model.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FavoriteController {

	private Button backButton;
	private Label textLabel;
	public ArrayList<Recipe> recipes;
	
	@FXML
	public VBox recipe_box;

	public void loadFavs() {
		for(Recipe r:recipes) {
			if(r.isFavorite()) {
				recipe_box.getChildren().add(r.getLabel());
			}			
		}
	}

	/***Returns the User to the Menu page***/
	//@FXML
	public void returnMenu(ActionEvent event) {

		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage)node.getScene().getWindow();
			stage.close();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/Main.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root, 800,800);
			MainController mainCon = loader.getController();
			mainCon.setRecipes(recipes);
		//	scene.getStylesheets().add(getClass().getResource("application/view/application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		}

		catch(IOException e)
		{
			System.out.println("Failed to switch scenes.");
			e.printStackTrace();
		}
	}

}
