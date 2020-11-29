package application.controller;

import java.io.IOException;
import java.util.ArrayList;

import application.model.Ingredient;
import application.model.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addIngredientController {
/****1st screen:
	- person enters info
	- person hits "next"
	- get 2nd screen controller
	- 2nd screen controller .collectInfo(info here)
	- 1st screen close*/

	Recipe recipe;
	private TextField recipeName;
	private TextField ingredientText;

	//all ingredients
	public void getIngredients() {
		String outputName = recipeName.getText();
		String outputIngredient = ingredientText.getText();
		ArrayList<Ingredient> arrIngredient = new ArrayList<Ingredient>();

		for (String x : outputIngredient.split("\n")) {

			String[] y = x.split(",");

			arrIngredient.add(new Ingredient(y[0] , y[1]));
		}
		recipe.setTitle(outputName);
		recipe.setIngredients(arrIngredient);

	}

	/***Returns the User to the Recipe page***/
	@FXML
	public void returnMenu(ActionEvent event) {

		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage)node.getScene().getWindow();
			stage.close();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root, 800,800);
			scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		}

		catch(IOException e)
		{
			System.out.println("Failed to switch scenes.");
			e.printStackTrace();
		}
	}

	/***Returns the User to the Recipe page***/
	@FXML
	public void pressNext(ActionEvent event) {


		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage)node.getScene().getWindow();
			stage.close();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Recipe.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root, 800,800);
			scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
			stage.setScene(scene);

			//get controller pass the info to the next window
			//create nonstandard
			RecipeController conR = loader.getController();
			conR.saveRecipe(recipe);
			//String

			stage.show();
		}

		catch(IOException e)
		{
			System.out.println("Failed to switch scenes.");
			e.printStackTrace();
		}
	}
}
