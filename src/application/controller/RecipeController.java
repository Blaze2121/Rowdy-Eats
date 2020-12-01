package application.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.Ingredient;
import application.model.Nutrition;
import application.model.Recipe;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RecipeController implements Initializable {

	@FXML
	private Button submitButton;

	@FXML
	private TextField recipeAmount;

	@FXML
	private TextField recipeProtein;

	@FXML
	private TextField recipeSize;

	@FXML
	private TextField recipeCh;

	@FXML
	private TextField recipeDFiber;

	@FXML
	private TextField recipeCal;

	@FXML
	private TextField recipeName;

	@FXML
	private TextField recipeSodium;

	@FXML
	private TextField recipeSugar;

	@FXML
	private TextField recipeCarb;

	@FXML
	private TextField recipeTotFat;

	@FXML
	private TextField recipeSFat;

	@FXML
	private TextField recipePrepTime;

	@FXML
	private TextField recipeCookTime;

	@FXML
	private TextField recipeCategory;

	@FXML
	private Button backButton;

	@FXML
	private Label titleLabel;

	Recipe recipe;
	Nutrition nutrition;

	Ingredient ingredient;//not sure on this one

	/**
	 * initialize() Used to start the program and load the data into the right method
	 * @param arg0 Used for JavaFx
	 * @param arg1 Used for JavaFx
	 */
	@Override
	public void initialize (URL arg0, ResourceBundle arg1)
	{
		/***load data****/
		try {

		}
		catch (Exception e)
		{
			System.out.println("Error Initialize=");
			e.printStackTrace();
		}
	}

	public void transferRecipe( Recipe recipe) {
		this.recipe = recipe;
		titleLabel.setText(recipe.getTitle());
	}

	/*SAVES THE INPUT FROM INGREDIENTS AND RECIPE*/
	public void saveRecipe() throws IOException {
		String inputAmount = recipeAmount.getText();
		String inputSize = recipeSize.getText();
		String inputCalories = recipeCal.getText();
		String inputTFat = recipeTotFat.getText();
		String inputSFat = recipeSFat.getText();
		String inputChol = recipeCh.getText();
		String inputSodium = recipeSodium.getText();
		String inputTCarb = recipeCarb.getText();
		String inputDFiber = recipeDFiber.getText();
		String inputSugar = recipeSugar.getText();
		String inputProtein = recipeProtein.getText();

		String inputCategory = recipeCategory.getText();
		String inputPrep = recipeProtein.getText();
		String inputCook = recipeProtein.getText();

		recipe.setCategory(inputCategory);
		recipe.setServing_size(inputSize);
		recipe.setPrep_time(inputPrep);
		recipe.setCook_time(inputCook);

		//Should it end with a newline?????
		String totalNutrition = inputAmount + "," + inputSize + "," + inputCalories + "," + inputTFat;
		totalNutrition = totalNutrition + "," + inputSFat + "," + inputChol + "," + inputSodium + "," + inputTCarb;
		totalNutrition = totalNutrition + "," + inputDFiber + "," + inputSugar + "," + inputProtein +"\n";

		//for (int i = 0; i < totalNutrition)

		//recipe.setNutritions(nutritions);
		/**************CREATES THE INGREDIENT/NUTRITION FILES********************/
		try {
			String ingredientName = recipe.getTitle() + "_ingredients.txt";
			String nutritionName = recipe.getTitle() + "_nutrition.txt";


			File ingredientFile = new File(ingredientName);
			File nutritionFile = new File(nutritionName);

			if (ingredientFile.createNewFile()) {
				FileWriter ingredientWrite = new FileWriter(ingredientFile);
				ingredientWrite.write(recipe.getIngredients().toString() +"\n"); //CORRECT???
				ingredientWrite.close();
			}
			if (nutritionFile.createNewFile()) {
				FileWriter nutritionWrite = new FileWriter(nutritionFile);
				nutritionWrite.write(totalNutrition);

				nutritionWrite.close();
			}
		}
		catch (IOException e) {
			System.out.println("ERROR: Ingredient/Nutrition=");
			e.printStackTrace();
		}
		/***********************************************/

		//Title,Course,Serving Size,Prep Time,Cook Time,Ingredient.txt,Nutrition.txt
		String totalRecipe = recipe.getTitle() + "," + recipe.getCategory() + "," + recipe.getServing_size() + "," + recipe.getPrep_time(); //NOT CORRECT SINCE INGREDIENTS SAVED IN RECIPE??? and not individual???Nevermind?
		totalRecipe = totalRecipe + ","+ recipe.getCook_time() + "," + recipe.getTitle() + "_ingredients.txt" + ","; // not sure about 20,90 for ingredient Amount
		totalRecipe = totalRecipe + recipe.getTitle() + "_nutrition.txt\n";

		try {
			FileWriter recipeFile = new FileWriter("data/recipe.txt", true);
			PrintWriter outFile = new PrintWriter(recipeFile);

			outFile.print(totalRecipe);

			recipeFile.close();
		}
		catch (IOException e) {
			System.out.println("ERROR: recipeFile=");
			e.printStackTrace();
		}
		//create the ingredients file
		// create the recipe file
		// append the ingredients and recipes file names into a recipe.txt (index file)
		//save the files
	}



	/***Returns the User to the addIngredients page***/
	//@FXML
	public void pressBack(ActionEvent event) {


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

		catch(IOException e)
		{
			System.out.println("Failed to switch back=");
			e.printStackTrace();
		}
	}

	/***
	 * Saves/Submits the user's input into the csv file then goes to main page
	 *
	 * ***/
	public void pressSubmit (ActionEvent event) {
		try {
			saveRecipe();
			Node node = (Node) event.getSource();
			Stage stage = (Stage)node.getScene().getWindow();
			stage.close();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/Main.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root, 800,800);
			scene.getStylesheets().add(getClass().getResource("/application/view/application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		}

		catch(IOException e)
		{
			System.out.println("Failed to switch scenes submit=");
			e.printStackTrace();
		}
	}











}
