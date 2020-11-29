package application.controller;

import application.model.Ingredient;
import application.model.Recipe;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class IngredientsController implements EventHandler
{
	@FXML
	private VBox box;

	@FXML
	private Label recipe_lb;

	@FXML
	private Button back_btn;

	public void setIngredients(Recipe r)
	{
		initControl();
		recipe_lb.setText(r.getName());
		ArrayList<Ingredient> ingredients = r.getIngredients();

		for(Ingredient i : ingredients)
		{
			Label l = new Label();
			l.setText(i.toString());
			box.getChildren().add(l);
		}
	}

	private void initControl()
	{
		box.getChildren().clear();
		recipe_lb.setText("");
	}


	/*Switches back to Main.fxml*/
	@Override
	public void handle(Event event)
	{
		try {
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
			System.out.println("Failed to switch scenes.");
			e.printStackTrace();
		}
	}
}