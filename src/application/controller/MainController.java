package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.model.Ingredient;
import application.model.Nutrition;
import application.model.Recipe;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
	private ArrayList<Recipe> recipes;
	private Label selected;
	public int cnt;

	public MainController()
	{

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initControls();
	}


	private void initControls()
	{
		if(recipes == null) {
			recipes = new ArrayList<Recipe>();
			loadAll();
		}
		else {
			for(Recipe r : recipes) {
				recipe_box.getChildren().add(r.getLabel());
			}
		}
		

	}
	
	public void loadAll() {
		cnt = 1;
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
			boolean favorite = Boolean.parseBoolean(pieces[7]);
			Recipe r = new Recipe(title, category, serving_size, prep_time, cook_time, favorite);
			r.addIngredients(Ingredient.load_ingredients(ingredients_file));
			Nutrition n = Nutrition.load_nutrition_info(nutrition_file);
			r.setNutrition(n);
			recipes.add(r);
		}

		for(Recipe r : recipes)
		{
			Label r_lb = new Label();
			r_lb.setText(r.isFavorite()?"* " + r.getName(): r.getName());
			r_lb.setId(r.getName());
			r_lb.setOnMouseEntered( e -> {
				r_lb.setScaleX(1.1);
				r_lb.setScaleY(1.1);
			});
			r_lb.setOnMouseExited( e -> {
				r_lb.setScaleX(1);
				r_lb.setScaleY(1);
			});
			r_lb.setOnMouseClicked(event -> {
				if(selected != null && selected != r_lb) 
				{
					cnt = 1;
					selected.setStyle("-fx-border-color: transparent;");
				}
				if(selected == r_lb) 
				{
					cnt += 1;
					
				}
				setSelected(r_lb);
				r_lb.setStyle("-fx-border-color: black;");
				
				for(Recipe rr : recipes)
				{
					if(r_lb.getId().equals(rr.getName()))
					{
						selected_recipe = rr;
						if(cnt >= 3) 
						{
							if(selected_recipe.isFavorite()) 
							{
								selected_recipe.setFavorite(false);
								r_lb.setText(selected_recipe.getName());
								
							}
							else 
							{
								selected_recipe.setFavorite(true);
								r_lb.setText("* " + selected_recipe.getName());
							}
							
							cnt = 0;
							break;
						}
					}
				}
				//System.out.println(selected.getText() + " " + String.valueOf(selected_recipe.isFavorite()) + " " + cnt );
			});
			//System.out.println("Adding new label");
			r.setLabel(r_lb);
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
			//	scene.getStylesheets().add(getClass().getResource("/application/view/application.css").toExternalForm());
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
				//scene.getStylesheets().add(getClass().getResource("/application/view/application.css").toExternalForm());
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
		//scene.getStylesheets().add(getClass().getResource("/application/view/application.css").toExternalForm());
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
		FavoriteController favCon = loader.getController();
		favCon.recipes = recipes;
		favCon.loadFavs();
		//scene.getStylesheets().add(getClass().getResource("/application/view/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		}
		catch (IOException e ) {
			System.out.print("Error in pressNBewRecipe=\n");
			e.printStackTrace();
		}

	}

	public ArrayList<Recipe> getRecipes() {
		return recipes;
	}



	public void setRecipes(ArrayList<Recipe> recipes) {
		this.recipes = recipes;
	}

	public Label getSelected() {
		return selected;
	}

	public void setSelected(Label selected) {
		this.selected = selected;
	}



}