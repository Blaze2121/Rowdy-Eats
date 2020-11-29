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

		ArrayList<String> foods = Recipe.load_recipes("data/recipe.txt");
		for(String food : foods)
		{
			//title, category, serving_size, prep_time, cook_time, ingredients_file_name.txt
			String[] pieces = food.split(",");

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

		for(Recipe r : recipes)
		{
			Label r_lb = new Label();
			r_lb.setText(r.getName());
			r_lb.setOnMouseClicked(event -> {
				System.out.println("Clicked!");
				for(Recipe rr : recipes)
				{
					if(r_lb.getText().equals(rr.getName()))
					{
						selected_recipe = rr;
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

				FXMLLoader loader = new FXMLLoader(getClass().getResource("../Ingredients.fxml"));
				AnchorPane root = (AnchorPane)loader.load();
				IngredientsController con = loader.getController();
				con.setIngredients(selected_recipe);
				Scene scene = new Scene(root);
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
	}

	public void handle_nutrition_scene(Event event)
	{
		if(selected_recipe != null)
		{
			try {
				Node node = (Node) event.getSource();
				Stage stage = (Stage)node.getScene().getWindow();
				stage.close();

				FXMLLoader loader = new FXMLLoader(getClass().getResource("../Nutrition.fxml"));
				AnchorPane root = (AnchorPane) loader.load();
				NutritionController con = loader.getController();
				con.setNutrition(selected_recipe);
				Scene scene = new Scene(root);
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
	}



	public static ArrayList<Recipe> getRecipes() {
		return recipes;
	}



	public static void setRecipes(ArrayList<Recipe> recipes) {
		MainController.recipes = recipes;
	}



}