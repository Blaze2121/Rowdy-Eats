package application.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.Ingredient;
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
	private Button backButton;

	@FXML
	private Label titleLabel;

	Recipe recipe;

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
			//String;
//			titleLabel.setText(recipe.getTitle()); //Correct? Label or get input again?
		}
		catch (Exception e)
		{
			System.out.println("Error Initialize=");
			e.printStackTrace();
		}
	}

	/*SAVES THE INPUT FROM INGREDIENTS AND RECIPE*/
	public void saveRecipe(Recipe recipe) throws IOException {			//IS THIS A FXML??
		String outputAmount = recipeAmount.getText();
		String outputSize = recipeSize.getText();
		String outputCalories = recipeCal.getText();
		String outputTFat = recipeTotFat.getText();
		String outputSFat = recipeSFat.getText();
		String outputChol = recipeCh.getText();
		String outputSodium = recipeSodium.getText();
		String outputTCarb = recipeCarb.getText();
		String outputDFiber = recipeDFiber.getText();
		String outputSugar = recipeSugar.getText();
		String outputProtein = recipeProtein.getText();

		//Should it end with a newline?????
		String totalNutrition = outputAmount + "," + outputSize + "," + outputCalories + "," + outputTFat;
		totalNutrition = totalNutrition + "," + outputSFat + "," + outputChol + "," + outputSodium + "," + outputTCarb;
		totalNutrition = totalNutrition + "," + outputDFiber + "," + outputSugar + "," + outputProtein +"\n";





		/**************CREATES THE INGREDIENT/NUTRITION FILES********************/
		try {

			File ingredientFile = new File(recipe.getTitle() + "_ingredients.txt"); //CORRECT??
			File nutritionFile = new File(recipe.getTitle() + "_nutrition.txt");

			if (ingredientFile.createNewFile()) {
				FileWriter ingredientWrite = new FileWriter(ingredientFile);
				ingredientWrite.write(recipe.getIngredients().toString() +"\n"); //fix?? CORRECT?

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

		String totalRecipe = recipe.getTitle() + "," + recipe.getIngredients().toString(); //NOT CORRECT SINCE INGREDIENTS SAVED IN RECIPE??? and not individual???Nevermind?
		totalRecipe = totalRecipe + ","+ recipe.getPrep_time() + "," + recipe.getCook_time() + "," + recipe.getTitle() + "_ingredients.txt" + ","; // not sure about 20,90 for ingredient Amount
		totalRecipe = totalRecipe + recipe.getTitle() + "_nutrition.txt";

		try {
			FileWriter recipeFile = new FileWriter("recipe.txt", true);
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
	@FXML
	public void pressBack(ActionEvent event) {


		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage)node.getScene().getWindow();
			stage.close();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("../addIngredients.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root, 800,800);
			scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		}

		catch(IOException e)
		{
			System.out.println("Failed to switch back=");
			e.printStackTrace();
		}
	}

	/***Saves/Submits the user's input into the csv file then goes to main page
	 *
	 * ***/
	public void pressSubmit (ActionEvent event) {

		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage)node.getScene().getWindow();
			stage.close();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root, 800,800);
			scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
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
