package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainController implements Initializable
{
	
	private ArrayList<Recipe> recipes;
	private Label selected_label;
	private Recipe selected_recipe;
	public int cnt;
	
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

	public MainController()
	{

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		recipes = new ArrayList<Recipe>();
		cnt = 1;
		
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
		Recipe.loadFav(recipes);

		//for(Recipe r : recipes)
		for (int i = 0; i < recipes.size(); i++)
		{
			Label r_lb = new Label();
			r_lb.setStyle(recipes.get(i).isFavorite() ? "-fx-text-fill: green;" : "-fx-text-fill: black;");
			r_lb.setText(recipes.get(i).getTitle());
			r_lb.setId(recipes.get(i).getTitle());
			r_lb.setOnMouseEntered( e -> {
				r_lb.setScaleX(1.1);
				r_lb.setScaleY(1.1);
			});
			r_lb.setOnMouseExited( e -> {
				r_lb.setScaleX(1);
				r_lb.setScaleY(1);
			});
			r_lb.setOnMouseClicked(event -> {
				mouseClick(r_lb);				
			});
			//System.out.println("Adding new label");
			recipe_box.getChildren().add(r_lb);
			recipes.get(i).setLabel(r_lb);
		}

	}

	private void mouseClick(Label r_lb) {
		System.out.print("Clicked! ");
		if(selected_label != r_lb) 
		{
			cnt = 0;
			if(selected_label !=null) {
				selected_label.setStyle("-fx-border-color: transparent;");
				selected_label.setTextFill(selected_recipe.isFavorite() ? Color.GREEN : Color.BLACK);
				
			}
								
			for (int j = 0; j < recipes.size(); j++)
			{
				if(r_lb.getText().equals(recipes.get(j).getTitle()))
				{
					selected_recipe = recipes.get(j);
					System.out.print("Selected Recipe is now " + selected_recipe.getTitle() + " ");
				}
			}
			setSelected_label(r_lb);
			selected_label.setStyle("-fx-border-color: black;");
			selected_label.setTextFill(selected_recipe.isFavorite() ? Color.GREEN : Color.BLACK);
		}
		cnt += 1;
		System.out.println(cnt);
		if(cnt == 3) {
			selected_recipe.switchFavorite();
			selected_label.setTextFill(selected_recipe.isFavorite() ? Color.GREEN : Color.BLACK);
			try {
				Recipe.saveFav(recipes);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			cnt = 0;
			System.out.println(selected_recipe.getTitle() + " Favorite " + String.valueOf(selected_recipe.isFavorite()) + "\nCount set back to 0");
		}
		
	}

	private void setSelected_label(Label lb) {
		this.selected_label = lb;
		
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
		FavoriteController favCon = loader.getController();
		favCon.loadFavs(recipes);

		stage.setScene(scene);
		stage.show();
		}
		catch (IOException e ) {
			System.out.print("Error in pressNBewRecipe=\n");
			e.printStackTrace();
		}

	}
	
	public void pickForMe(ActionEvent event) {
		Random random = new Random(); 
		int num = random.nextInt(recipes.size());
		System.out.println(num);
		mouseClick(recipes.get(num).getLabel());
		
	}

	public ArrayList<Recipe> getRecipes() {
		return recipes;
	}



	public void setRecipes(ArrayList<Recipe> recipes) {
		this.recipes = recipes;
	}



}