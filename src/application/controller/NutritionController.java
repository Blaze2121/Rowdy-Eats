package application.controller;

import application.model.Nutrition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
	
	
	public void setNutrition(Nutrition n)
	{
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
	}
	
	@Override
	public void handle(Event arg0) {
		// TODO Auto-generated method stub
	}
	
}