package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.model.Ingredient;
import application.model.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddIngredientsController implements Initializable {
/****1st screen:
	- person enters info
	- person hits "next"
	- get 2nd screen controller
	- 2nd screen controller .collectInfo(info here)
	- 1st screen close*/

	Recipe recipe;

	@FXML
	private TextField recipeName;
	@FXML
	private TextArea ingredientTextArea;
	@FXML
	private Button IngredientBButton;
	@FXML
	private Button ingredientButton;

	public void initialize(URL location, ResourceBundle resources) {
		this.recipe = new Recipe();
	}

	//all ingredients
	public void getIngredients() {
		String outputName = recipeName.getText();
		String outputIngredient = ingredientTextArea.getText();
		ArrayList<Ingredient> arrIngredient = new ArrayList<Ingredient>();

		String[] newLine = outputIngredient.split("\\n");
		for (int i = 0; i < newLine.length; i+=2) {
			arrIngredient.add(new Ingredient(newLine[i], newLine[i+1]));
		}

		this.recipe.setTitle(outputName);
		this.recipe.setIngredients(arrIngredient);

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
			//scene.getStylesheets().add(getClass().getResource("application/view/application.css").toExternalForm());
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
	//@FXML
	public void pressNext(ActionEvent event) {

		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage)node.getScene().getWindow();

			stage.close();

			stage = new Stage();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/Recipe.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root, 800,800);
		//	scene.getStylesheets().add(getClass().getResource("application/view/application.css").toExternalForm());
			stage.setScene(scene);
			//get controller pass the info to the next window
			//create nonstandard
			RecipeController conR = loader.getController();
			getIngredients();
			conR.transferRecipe(this.recipe);
			//String

			stage.show();
		}

		catch(IOException e)
		{
			System.out.println("Failed to switch scenes.");
			e.printStackTrace();
		}
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public TextField getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(TextField recipeName) {
		this.recipeName = recipeName;
	}

	public TextArea getIngredientTextArea() {
		return ingredientTextArea;
	}

	public void setIngredientTextArea(TextArea ingredientTextArea) {
		this.ingredientTextArea = ingredientTextArea;
	}

	public Button getIngredientBButton() {
		return IngredientBButton;
	}

	public void setIngredientBButton(Button ingredientBButton) {
		IngredientBButton = ingredientBButton;
	}

	public Button getIngredientButton() {
		return ingredientButton;
	}

	public void setIngredientButton(Button ingredientButton) {
		this.ingredientButton = ingredientButton;
	}
}
