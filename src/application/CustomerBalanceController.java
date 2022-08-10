package application;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CustomerBalanceController {
	Stage applicationStage;
	private Customer customerInUse;

	private DataCenter dc = new DataCenter();

    @FXML
    private TextArea customernumberTextField;

    @FXML
    private ChoiceBox<Integer> depositAmountChoiceBox;

    @FXML
    private ChoiceBox<String> machinenumberChoiceBox;
    
    @FXML
    private Label logInErrorMessage, loginSuccessDisplay, machineLabel, addMoneyLabel;
    
    @FXML
    private Button availabilityButton;
    
    
    
    
    
    Label newAccountErrorDisplay = new Label();
    
    @FXML
    void Login(ActionEvent event) {
    	logInErrorMessage.setText("");
    	loginSuccessDisplay.setText("");
    	addMoneyLabel.setText("");
    	machineLabel.setText("");
    	String customernumber =  customernumberTextField.getText();
    	
    	if (customernumber == "") {
    		logInErrorMessage.setText("Please enter your account number.");
    	}else {
    		customerInUse = dc.getCustomer(customernumber);
        	if (customerInUse==null) {
        		customerInUse = dc.getCustomer(customernumber);
        		logInErrorMessage.setText("Account does not exist, please create a new account.");	
        	}else {
        		loginSuccessDisplay.setText("Your current balance: " + customerInUse.getBalance());
        	}
    	}
    	machinenumberChoiceBox.setValue(null);
    	depositAmountChoiceBox.setValue(null);

    }
    
    @FXML
    void Newaccount(ActionEvent Createaccountevent) {
    	Scene mainScene = applicationStage.getScene();
    	
    	VBox newAccountView = new VBox();
    	Label newAccountLabel = new Label("Please enter a 4-digit customerID you would like to have: ");
    	
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
		String outcome = dc.addCustomer(textfield.getText());
		if (outcome.equals("")) applicationStage.setScene(mainScene);
		else newAccountErrorDisplay.setText(outcome);
	}

	@FXML
    void deposit(ActionEvent event) {
		addMoneyLabel.setTextFill(Color.RED);
		addMoneyLabel.setText("");
		try {
			int amountToDeposit = depositAmountChoiceBox.getValue();
			if (customerInUse == null)
				addMoneyLabel.setText("Please log in first.");
			else {
				customerInUse.addMoney(amountToDeposit);
				addMoneyLabel.setTextFill(Color.BLACK);
				addMoneyLabel.setText("Success!");
				loginSuccessDisplay.setText("Your current balance: " + customerInUse.getBalance());
			}
				
		}catch(NullPointerException e) {
			addMoneyLabel.setText("Please choose an amount from the choice box to deposit.");
		}
    }

    @FXML
    void Start(ActionEvent event) {
    	machineLabel.setText("");
    	try {
    		String machinenumber = machinenumberChoiceBox.getValue();
        	Machine m = dc.getMachine(machinenumber);
        	if (customerInUse.getBalance()<m.getPricePerUse())
        		machineLabel.setText("Insufficient fund, please deposit more money.");
        	else if (!m.getInUse()) {
        		customerInUse.addMoney(-m.getPricePerUse());
        		loginSuccessDisplay.setText("Your new balance:" + customerInUse.getBalance());
        		machineLabel.setText("Payment Approved.");
        		m.setInUse(true);
        	    m.setCustomerID(customerInUse.getCustomerID());
        	}
        	else {
        		machineLabel.setText("Machine is in use, please choose another one.");
        	}
    	}catch(NullPointerException e) {
    		machineLabel.setText("Please select a machine frist.");
    	}
    	
    	
    }

    @FXML
    void End(ActionEvent event) {
    	machineLabel.setText("");
    	try {
    		String machinenumber = machinenumberChoiceBox.getValue();
        	Machine m = dc.getMachine(machinenumber);
        	if (!m.getInUse()) {
        		machineLabel.setText("Machine is not in use.");
        	}else if(!m.getCustomerID().equals(customerInUse.getCustomerID())) {
        		machineLabel.setText("Machine is in use, please choose another one.");
        	}else {
        		machineLabel.setText("Machine is ended. Thanks for using.");
        		m.setInUse(false);
        		m.setCustomerID("");
        	}
    	}catch(NullPointerException e) {
    		machineLabel.setText("Please select a machine frist.");
    	}


    }
    
    @FXML
    void checkAvailable(ActionEvent event) {
    	machineLabel.setText("");
    	String availableMachines = "Available machines:";
    	for (Machine m: dc.getAllMachines())
    		if (!m.getInUse())
    			availableMachines = availableMachines +"\n" + m.getName();
    	machineLabel.setText(availableMachines);
    }

}
