package application.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Nutrition
{
	private String amt_per_serving, serving_size, calories;
	private String total_fat, saturated_fat, cholesterol; 
	private String sodium, total_carbs, dietary_fiber, sugar_amt, protein_amt;
	
	private String recipe_name = "";
	
	
	public Nutrition(String amt_per_serving, String serving_size, String calories, 
					String total_fat, String saturated_fat, String cholesterol, 
					String sodium, String total_carbs, String dietary_fiber,
					String sugar_amt, String protein_amt)
	{
		this.amt_per_serving = amt_per_serving;
		this.serving_size = serving_size;
		this.calories = calories;
		this.total_fat = total_fat;
		this.saturated_fat = saturated_fat;
		this.cholesterol = cholesterol;
		this.sodium = sodium;
		this.total_carbs = total_carbs;
		this.dietary_fiber = dietary_fiber;
		this.sugar_amt = sugar_amt;
		this.protein_amt = protein_amt;
	}
	
	public static Nutrition load_nutrition_info(String filename)
	{
		Nutrition ret = null;
		try {
		BufferedReader file_reader = new BufferedReader(new FileReader(filename));
        String content = file_reader.readLine();
        while (content != null)
        {
        	//the format is the order of the variables in this class
        	String[] p = content.split(",");
        	ret = new Nutrition(p[0], p[1], p[2], p[3],
        						p[4], p[5], p[6], p[7],
        						p[8], p[9], p[11]);
            content = file_reader.readLine();
        }
        file_reader.close();
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	public String getAmt_per_serving() {
		return amt_per_serving;
	}
	public void setAmt_per_serving(String amt_per_serving) {
		this.amt_per_serving = amt_per_serving;
	}
	public String getServing_size() {
		return serving_size;
	}
	public void setServing_size(String serving_size) {
		this.serving_size = serving_size;
	}
	public String getCalories() {
		return calories;
	}
	public void setCalories(String calories) {
		this.calories = calories;
	}
	public String getTotal_fat() {
		return total_fat;
	}
	public void setTotal_fat(String total_fat) {
		this.total_fat = total_fat;
	}
	public String getSaturated_fat() {
		return saturated_fat;
	}
	public void setSaturated_fat(String saturated_fat) {
		this.saturated_fat = saturated_fat;
	}
	public String getCholesterol() {
		return cholesterol;
	}
	public void setCholesterol(String cholesterol) {
		this.cholesterol = cholesterol;
	}
	public String getSodium() {
		return sodium;
	}
	public void setSodium(String sodium) {
		this.sodium = sodium;
	}
	public String getTotal_carbs() {
		return total_carbs;
	}
	public void setTotal_carbs(String total_carbs) {
		this.total_carbs = total_carbs;
	}
	public String getDietary_fiber() {
		return dietary_fiber;
	}
	public void setDietary_fiber(String dietary_fiber) {
		this.dietary_fiber = dietary_fiber;
	}
	public String getSugar_amt() {
		return sugar_amt;
	}
	public void setSugar_amt(String sugar_amt) {
		this.sugar_amt = sugar_amt;
	}
	public String getProtein_amt() {
		return protein_amt;
	}
	public void setProtein_amt(String protein_amt) {
		this.protein_amt = protein_amt;
	}

	public String getRecipeName() {
		return recipe_name;
	}

	public void setRecipeName(String recipe_name) {
		this.recipe_name = recipe_name;
	}
	
	
	
}