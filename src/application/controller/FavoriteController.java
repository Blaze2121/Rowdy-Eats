package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.model.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FavoriteController{

	@FXML
	private Button backButton;
	@FXML
	private Label textLabel;
	@FXML
	private VBox vBox;
	
	private ArrayList<Recipe> recipes;
	private ArrayList<Label> labels;

	public void loadFavs(ArrayList<Recipe> p, ArrayList<Label> b) {
		setRecipes(p);
		setLabels(b);
		System.out.println("Trying to add labels to favorites");
		for(Recipe r : recipes) {
			if(r.isFavorite()) {
				for(Label l : labels) { 
					if(r.getTitle().equalsIgnoreCase(l.getText())) {
						System.out.println(l.getText() + " label added to favorites");
						vBox.getChildren().add(l);
					}
				}
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



	public ArrayList<Recipe> getRecipes() {
		return recipes;
	}



	public void setRecipes(ArrayList<Recipe> recipes) {
		this.recipes = recipes;
	}



	public ArrayList<Label> getLabels() {
		return labels;
	}



	public void setLabels(ArrayList<Label> labels) {
		this.labels = labels;
	}

}
