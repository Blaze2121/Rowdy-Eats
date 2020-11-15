package application.controller;

import java.io.IOException;
import java.util.ArrayList;

import application.model.Ingredient;
import application.model.Nutrition;
import application.model.Recipe;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainController implements EventHandler
{
	
	@FXML
	private HBox recipe_box;
	
	private Recipe selected_recipe;
	private ArrayList<Recipe> recipes;
	public MainController()
	{
		recipes = new ArrayList<Recipe>();
		selected_recipe = null;
		initControls();
		
		for(Recipe r : recipes)
		{
			Label r_lb = new Label();
			r_lb.setText(r.getName());
			r_lb.setOnMouseClicked(event -> {
				for(Recipe rr : recipes)
				{
					if(r_lb.getText().equals(rr.getName()))
					{
						selected_recipe = rr;
					}
				}
			});
			recipe_box.getChildren().add(r_lb);
		}
	}
	
	public void set_labels()
	{
		for(Recipe r : recipes)
		{
			Label r_lb = new Label();
			r_lb.setText(r.getName());
			r_lb.setOnMouseClicked(event -> {
				for(Recipe rr : recipes)
				{
					if(r_lb.getText().equals(rr.getName()))
					{
						selected_recipe = rr;
					}
				}
			});
			recipe_box.getChildren().add(r_lb);
		}
	}
	
	private void initControls()
	{
		recipe_box.getChildren().clear();
		
		ArrayList<String> foods = Recipe.load_recipes("recipe.txt");
		for(String food : foods)
		{
			//title, category, serving_size, prep_time, cook_time, ingredients_file_name.txt
			String[] pieces = food.split(",");
			
			String title = pieces[0];
			String category = pieces[1];
			String serving_size = pieces[2];
			String prep_time = pieces[3];
			String cook_time = pieces[4];
			String ingredients_file = pieces[5];
			String nutrition_file = pieces[6];
			Recipe r = new Recipe(title, category, serving_size, prep_time, cook_time);
			r.addIngredients(Ingredient.load_ingredients(ingredients_file));
			Nutrition n = Nutrition.load_nutrition_info(nutrition_file);
			r.setNutrition(n);
			recipes.add(r);
		}
	}
	
	@Override
	public void handle(Event event) 
	{
		
	}
	
	
	public void handle_nutrition(Event event)
	{
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage)node.getScene().getWindow();
			stage.close();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main.fxml"));
			Parent root = loader.load();
			NutritionController con = loader.getController();
			con.setNutrition(selected_recipe);
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
}