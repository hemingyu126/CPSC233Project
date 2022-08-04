package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CustomerBalanceController {
	private DataCenter dc = new DataCenter();

    @FXML
    private TextArea customernumberTextField;

    @FXML
    private TextField depositamountTextField;

    @FXML
    private ChoiceBox<String> machinenumberChoiceBox;
    
    @FXML
    private Label balanceDisplay;

    @FXML
    void DEPOSIT(ActionEvent event) {
    	String custmornumber =  customernumberTextField.getText();
    	String depositamount = depositamountTextField.getText();
    	
    }

    @FXML
    void Start(ActionEvent event) {

    }

    @FXML
    void Pause(ActionEvent event) {

    }

}
