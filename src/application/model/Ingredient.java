package application.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ingredient {
	private String ingredient;
	private String amount;
	
	public Ingredient(String ingredient, String amount)
	{
		this.ingredient = ingredient;
		this.amount = amount;
	}

	public static ArrayList<Ingredient> load_ingredients(String filename) throws IOException
	{
		ArrayList<Ingredient> ret = new ArrayList<Ingredient>();
		BufferedReader file_reader = new BufferedReader(new FileReader(filename));
        String content = file_reader.readLine();
        while (content != null)
        {
        	//the format is
        	//Ingredient_name, amount(in strings)
        	String[] pieces = content.split(",");
        	String ingredient_name = pieces[0];
        	String amount = pieces[1];
        	Ingredient i = new Ingredient(ingredient_name, amount);
        	ret.add(i);
            content = file_reader.readLine();
        }
        file_reader.close();
        return ret;
	}
	
	public String getIngredient() {
		return ingredient;
	}
	
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}