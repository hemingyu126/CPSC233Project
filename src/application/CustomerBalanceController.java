package application;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CustomerBalanceController {
	Stage applicationStage;
	
	private DataCenter dc = new DataCenter();

    @FXML
    private TextArea customernumberTextField;

    @FXML
    private ChoiceBox<Integer> depositamountChoiceBox;

    @FXML
    private ChoiceBox<?> machinenumberChoiceBox;
    
    @FXML
    void Login(ActionEvent event) {
    	String customernumber =  customernumberTextField.getText();
    	

    }
    
    @FXML
    void Newaccount(ActionEvent Createaccountevent) {
    	Scene mainScene = applicationStage.getScene();
    	
    	HBox Createaccount = new HBox();
    	Label NewaccountLabel = new Label("Please enter the customerID you would like to have:  ");
    	TextField NewaccountTextField = new TextField();
    	
    	Button CompleteButton = new Button("Complete");
    	CompleteButton.setOnAction(CompleteEvent -> applicationStage.setScene(mainScene));
    	
    	Createaccount.getChildren().addAll(NewaccountLabel, NewaccountTextField, CompleteButton);
    	Scene Newaccount = new Scene(Createaccount);
    	applicationStage.setScene(Newaccount);
    }

    @FXML
    void DEPOSIT(ActionEvent event) {
    	double balance = 0.0;
    	int depositamount = depositamountChoiceBox.getValue();
    	balance = balance + depositamount;
    	
    	

    }

    @FXML
    void Start(ActionEvent event) {

    }

    @FXML
    void Pause(ActionEvent event) {

    }

}
