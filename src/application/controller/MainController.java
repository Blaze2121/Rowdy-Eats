package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.model.Ingredient;
import application.model.Nutrition;
import application.model.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController implements Initializable
{

	@FXML
	private VBox recipe_box;

	@FXML
	private Button nut_btn;

	@FXML
	private Button ing_btn;

	@FXML
	private Button newRecipeButton;

	@FXML
	private Button favoriteButton;

	private Recipe selected_recipe;
	private static ArrayList<Recipe> recipes;

	public MainController()
	{

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		recipes = new ArrayList<Recipe>();
		selected_recipe = null;

		initControls();
	}


	private void initControls()
	{

		ArrayList<String> foods = Recipe.loadRecipes("data/recipe.txt");
		//for(String food : foods)
		for(int i = 1; i < foods.size(); i++)
		{
			//title, category, serving_size, prep_time, cook_time, ingredients_file_name.txt
			String[] pieces = foods.get(i).split(",");

			String title = pieces[0];
			String category = pieces[1];
			String serving_size = pieces[2];
			String prep_time = pieces[3];
			String cook_time = pieces[4];
			String ingredients_file = "data/" + pieces[5];
			String nutrition_file = "data/" + pieces[6];
			Recipe r = new Recipe(title, category, serving_size, prep_time, cook_time);
			r.addIngredients(Ingredient.load_ingredients(ingredients_file));
			Nutrition n = Nutrition.load_nutrition_info(nutrition_file);
			r.setNutrition(n);
			recipes.add(r);
		}

		//for(Recipe r : recipes)
		for (int i = 0; i < recipes.size(); i++)
		{
			Label r_lb = new Label();
			r_lb.setText(recipes.get(i).getTitle());
			r_lb.setOnMouseClicked(event -> {
				System.out.println("Clicked!");

				//IF USER CLICKS LABEL 3 Times then add to favorite??? Could be a good idea

				//for(Recipe rr : recipes)
				for (int j = 0; j < recipes.size(); j++)
				{
					if(r_lb.getText().equals(recipes.get(j).getTitle()))
					{
						selected_recipe = recipes.get(j);
					}
				}
			});
			//System.out.println("Adding new label");
			recipe_box.getChildren().add(r_lb);
		}

	}

	public void handle_ingredients_scene(Event evt)
	{
		if(selected_recipe != null)
		{
			try {
				Node node = (Node) evt.getSource();
				Stage stage = (Stage)node.getScene().getWindow();
				stage.close();

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/Ingredients.fxml"));
				AnchorPane root = (AnchorPane)loader.load();
				IngredientsController con = loader.getController();
				con.setIngredients(selected_recipe);
				Scene scene = new Scene(root);

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

	public void handle_nutrition_scene(Event event)
	{
		if(selected_recipe != null)
		{
			try {
				Node node = (Node) event.getSource();
				Stage stage = (Stage)node.getScene().getWindow();
				stage.close();

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/Nutrition.fxml"));
				AnchorPane root = (AnchorPane) loader.load();
				NutritionController con = loader.getController();
				con.setNutrition(selected_recipe);
				Scene scene = new Scene(root);

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

	public void pressNewRecipe(ActionEvent event) {
		try {
		Node node = (Node) event.getSource();
		Stage stage = (Stage)node.getScene().getWindow();
		stage.close();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/AddIngredients.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root, 800,800);

		stage.setScene(scene);
		stage.show();
		}
		catch (IOException e ) {
			System.out.print("Error in pressNBewRecipe=\n");
			e.printStackTrace();
		}

	}

	public void pressFavorite(ActionEvent event) {
		try {
		Node node = (Node) event.getSource();
		Stage stage = (Stage)node.getScene().getWindow();
		stage.close();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/Favorite.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root, 800,800);

		stage.setScene(scene);
		stage.show();
		}
		catch (IOException e ) {
			System.out.print("Error in pressNBewRecipe=\n");
			e.printStackTrace();
		}

	}

	public static ArrayList<Recipe> getRecipes() {
		return recipes;
	}



	public static void setRecipes(ArrayList<Recipe> recipes) {
		MainController.recipes = recipes;
	}



}