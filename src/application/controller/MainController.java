package application.controller;

import java.io.IOException;
import java.util.ArrayList;

import application.model.Ingredient;
import application.model.Nutrition;
import application.model.Recipe;
import javafx.event.Event;
import javafx.event.EventHandler;

public class MainController implements EventHandler
{
	private ArrayList<Recipe> recipes;
	public MainController()
	{
		recipes = new ArrayList<Recipe>();
		
		initControls();
	}
	
	private void initControls()
	{
		try {
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
				ArrayList<Ingredient> ingredients = Ingredient.load_ingredients(ingredients_file);
				for(Ingredient i : ingredients)
				{
					r.addIngredient(i);
				}
				Nutrition n = Nutrition.load_nutrition_info(nutrition_file);
				r.setNutrition(n);
				recipes.add(r);
			}
		}
		catch(IOException e)
		{
			System.out.println("Failed to load recipes.txt");
			e.printStackTrace();
		}
	}
	
	@Override
	public void handle(Event event) 
	{
		
	}
}