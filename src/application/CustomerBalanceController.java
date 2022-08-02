package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CustomerBalanceController {

    @FXML
    private TextArea customernumberTextField;

    @FXML
    private TextField depositamountTextField;

    @FXML
    private ChoiceBox<?> machinenumberChoiceBox;

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
