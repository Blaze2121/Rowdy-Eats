package application.controller;

import java.io.IOException;

import application.model.Nutrition;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class NutritionController implements EventHandler
{
	@FXML
	private Button back_btn;
	
	@FXML
	private Label recipe_lb;
	
	@FXML
	private Label amt_lb;
	
	@FXML
	private Label serv_lb;
	
	@FXML
	private Label cal_lb;
	
	@FXML
	private Label tf_lb;
	
	@FXML
	private Label sf_lb;
	
	@FXML
	private Label ch_lb;
	
	@FXML
	private Label sod_lb;
	
	@FXML
	private Label tc_lb;
	
	@FXML
	private Label df_lb;
	
	@FXML
	private Label sug_lb;
	
	@FXML
	private Label pro_lb;
	
	@FXML
	private HBox recipe_box;
	
	public void setNutrition(Recipe r)
	{
		Nutrition n = r.getNutrition();
		initControl();
		amt_lb.setText(n.getAmt_per_serving());
		serv_lb.setText(n.getServing_size());
		cal_lb.setText(n.getCalories());
		tf_lb.setText(n.getTotal_fat());
		sf_lb.setText(n.getSaturated_fat());
		ch_lb.setText(n.getCholesterol());
		sod_lb.setText(n.getSodium());
		tc_lb.setText(n.getTotal_carbs());
		df_lb.setText(n.getDietary_fiber());
		sug_lb.setText(n.getSugar_amt());
		pro_lb.setText(n.getProtein_amt());
		recipe_lb.setText(n.getRecipeName());
	}
	
	private void initControl()
	{
		amt_lb.setText("");
		serv_lb.setText("");
		cal_lb.setText("");
		tf_lb.setText("");
		sf_lb.setText("");
		ch_lb.setText("");
		sod_lb.setText("");
		tc_lb.setText("");
		df_lb.setText("");
		sug_lb.setText("");
		pro_lb.setText("");
		recipe_lb.setText("");
	}
	
	public void go_back(Event event)
	{
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage)node.getScene().getWindow();
			stage.close();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main.fxml"));
			Parent root = loader.load();
			///PersonnelController con = loader.getController();
			///con.setUser(u);
			Scene scene = new Scene(root, 800,800);
			scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		}
		
		catch(IOException e)
		{
			System.out.println("Failed to switch scenes.");
			e.printStackTrace();
		}
	}
	
	@Override
	public void handle(Event arg0) {
		// TODO Auto-generated method stub
	}
	
}