package application.controller;

import java.io.IOException;
import java.util.ArrayList;
import application.model.Recipe;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FavoriteController{

	@FXML
	private Button backButton;
	@FXML
	private Label textLabel;
	@FXML
	private VBox vBox;
	
	private ArrayList<Recipe> recipes;
	private Label selected_label;
	private Recipe selected_recipe;
	public int cnt;

	public void loadFavs(ArrayList<Recipe> p) {
		setRecipes(p);
		System.out.println("Trying to add labels to favorites");
		for(Recipe r : recipes) {
			if(r.isFavorite()) {
				Label r_lb = new Label();
				r_lb.setStyle(r.isFavorite() ? "-fx-text-fill: green;" : "-fx-text-fill: black;");
				r_lb.setText(r.getTitle());
				r_lb.setId(r.getTitle());
				r_lb.setOnMouseEntered( e -> {
					r_lb.setScaleX(1.1);
					r_lb.setScaleY(1.1);
				});
				r_lb.setOnMouseExited( e -> {
					r_lb.setScaleX(1);
					r_lb.setScaleY(1);
				});
				r_lb.setOnMouseClicked(event -> {
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
					
					
				});
				vBox.getChildren().add(r_lb);
			}
		}
	}



	/***Returns the User to the Menu page***/
	@FXML
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
	
	@FXML
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
		else {
			System.out.println(selected_recipe);
		}
	}
	
	@FXML
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



	public ArrayList<Recipe> getRecipes() {
		return recipes;
	}



	public void setRecipes(ArrayList<Recipe> recipes) {
		this.recipes = recipes;
	}



	public Label getSelected_label() {
		return selected_label;
	}



	public void setSelected_label(Label selected_label) {
		this.selected_label = selected_label;
	}



	public Recipe getSelected_recipe() {
		return selected_recipe;
	}



	public void setSelected_recipe(Recipe selected_recipe) {
		this.selected_recipe = selected_recipe;
	}

}
