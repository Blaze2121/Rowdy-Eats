package application.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Recipe
{
	private String title, category, serving_size, prep_time, cook_time;
	private ArrayList<Ingredient> ingredients;
	private Nutrition nutrition_info;
	public Recipe(String title, String category, String serving_size, String prep_time, String cook_time)
	{
		nutrition_info = null;
		ingredients = new ArrayList<Ingredient>();
		this.title = title;
		this.category = category;
		this.serving_size = serving_size;
		this.prep_time = prep_time;
		this.cook_time = cook_time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getServing_size() {
		return serving_size;
	}

	public void setServing_size(String serving_size) {
		this.serving_size = serving_size;
	}

	public String getPrep_time() {
		return prep_time;
	}

	public void setPrep_time(String prep_time) {
		this.prep_time = prep_time;
	}

	public String getCook_time() {
		return cook_time;
	}

	public void setCook_time(String cook_time) {
		this.cook_time = cook_time;
	}

	public Nutrition getNutrition_info() {
		return nutrition_info;
	}

	public void setNutrition_info(Nutrition nutrition_info) {
		this.nutrition_info = nutrition_info;
	}

	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public void setNutrition(Nutrition n)
	{
		nutrition_info = n;
		nutrition_info.setRecipeName(title);
	}

	public Nutrition getNutrition()
	{
		return nutrition_info;
	}

	public String getName()
	{
		return this.title;
	}

	public void clearIngredients()
	{
		ingredients.clear();
	}

	public void addIngredient(Ingredient i)
	{
		ingredients.add(i);
	}

	public void addIngredients(ArrayList<Ingredient> ingredients)
	{
		for(Ingredient i : ingredients)
		{
			addIngredient(i);
		}
	}

	public ArrayList<Ingredient> getIngredients()
	{
		return this.ingredients;
	}

	public static ArrayList<String> load_recipes(String filename)
	{
		System.out.println("Loading recipes...");
		try {
			ArrayList<String> ret = new ArrayList<String>();
			BufferedReader file_reader = new BufferedReader(new FileReader(filename));
	        String content = file_reader.readLine();
	        while (content != null)
	        {
	        	ret.add(content);
	            content = file_reader.readLine();
	        }
	        file_reader.close();
	        return ret;
		}

		catch(IOException e)
		{
			e.printStackTrace();
		}

		System.out.println("Finished loading.");

		return new ArrayList<String>();
	}

	public void setServingSize(String size)
	{
		this.serving_size = size;
	}

	public void setPrepTime(String time)
	{
		this.prep_time = time;
	}

	public void setCookTime(String time)
	{
		this.cook_time = time;
	}

	public String getPrepTime()
	{
		return this.prep_time;
	}

	public String getCookTime()
	{
		return this.cook_time;
	}

	public String getServingSize()
	{
		return this.serving_size;
	}
}