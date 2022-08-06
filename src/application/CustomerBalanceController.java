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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CustomerBalanceController {
	Stage applicationStage;
	Customer customerInUse;

	private DataCenter dc = new DataCenter();

    @FXML
    private TextArea customernumberTextField;

    @FXML
    private ChoiceBox<Integer> depositamountChoiceBox;

    @FXML
    private ChoiceBox<?> machinenumberChoiceBox;
    
    @FXML
    private Label logInErrorMessage;
    
    @FXML
    private Label loginSuccessDisplay;
    
    Label newAccountErrorDisplay = new Label();
    
    @FXML
    void Login(ActionEvent event) {
    	logInErrorMessage.setText("");
    	loginSuccessDisplay.setText("");
    	String customernumber =  customernumberTextField.getText();
    	customerInUse = dc.getCustomer(customernumber);
    	if (customerInUse==null) {
    		logInErrorMessage.setText("Account does not exist, please create a new account.");	
    	}else {
    		loginSuccessDisplay.setText("Your current balance: " + customerInUse.getBalance());
    	}

    }
    
    @FXML
    void Newaccount(ActionEvent Createaccountevent) {
    	Scene mainScene = applicationStage.getScene();
    	
    	VBox newAccountView = new VBox();
    	Label newAccountLabel = new Label("Please enter the customerID you would like to have: ");
    	
    	newAccountErrorDisplay.setTextFill(Color.RED);
    	
    	HBox Createaccount = new HBox();
    	TextField NewaccountTextField = new TextField();
    	
    	Button CompleteButton = new Button("Complete");
    	CompleteButton.setOnAction(CompleteEvent -> createNewAccount(mainScene, NewaccountTextField));
    	
    	Createaccount.getChildren().addAll(NewaccountTextField, CompleteButton);
    	newAccountView.getChildren().addAll(newAccountLabel, Createaccount, newAccountErrorDisplay);
    	Scene Newaccount = new Scene(newAccountView);
    	applicationStage.setScene(Newaccount);
    }


    private void createNewAccount(Scene mainScene, TextField textfield) {
    	newAccountErrorDisplay.setText("");
		String proposedID = textfield.getText();
		Customer newCustomer = new Customer(proposedID);
		boolean outcome = dc.addCustomer(newCustomer);
		if (!outcome) {
			newAccountErrorDisplay.setText("Account already exist, please try another account.");
		}
		applicationStage.setScene(mainScene);
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
