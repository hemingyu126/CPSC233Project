package application;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LaundryRoomController {
	Stage applicationStage;
	private Customer customerInUse;

	private DataCenter dc = new DataCenter();

    @FXML
    private TextArea customernumberTextField;

    @FXML
    private ChoiceBox<Integer> depositAmountChoiceBox;

    @FXML
    private ChoiceBox<String> machinenumberChoiceBox, washModeChoiceBox, temperatureChoiceBox;
    
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
    	// Read input
    	String customernumber =  customernumberTextField.getText();
    	
    	// if the input is empty
    	if (customernumber == "") {
    		logInErrorMessage.setText("Please enter your account number.");
    	}else {
    		customerInUse = dc.getCustomer(customernumber);
    		
    		// if customer of the input ID cannot be found
        	if (customerInUse==null) {
        		customerInUse = dc.getCustomer(customernumber);
        		logInErrorMessage.setText("Account does not exist, please create a new account.");
        	}else {
        		// Customer file found. Display the account balance.
        		loginSuccessDisplay.setText("Your current balance: " + customerInUse.getBalance());
        	}
    	}
    	
    	// All choice boxes should be set to empty once a new customer logs in
    	machinenumberChoiceBox.setValue(null);
    	depositAmountChoiceBox.setValue(null);
    	washModeChoiceBox.setValue(null);
    	temperatureChoiceBox.setValue(null);

    }
    
    @FXML
    void newAccount(ActionEvent Createaccountevent) {
    	Scene mainScene = applicationStage.getScene();
    	
    	// Build a new scene for creating a new account page.
    	VBox newAccountView = new VBox();
    	Label newAccountLabel = new Label("Please enter a 4-digit customerID you would like to have: ");
    	
    	newAccountErrorDisplay.setTextFill(Color.RED);
    	
    	HBox Createaccount = new HBox();
    	TextField NewaccountTextField = new TextField();
    	
    	Button CompleteButton = new Button("Complete");
    	CompleteButton.setOnAction(CompleteEvent -> createNewAccount(mainScene, NewaccountTextField));
    	
    	Createaccount.getChildren().addAll(NewaccountTextField, CompleteButton);
    	newAccountView.getChildren().addAll(newAccountLabel, Createaccount, newAccountErrorDisplay);
    	// Change the scene
    	Scene Newaccount = new Scene(newAccountView);
    	applicationStage.setScene(Newaccount);
    }


    private void createNewAccount(Scene mainScene, TextField textfield) {
    	newAccountErrorDisplay.setText("");
    	// Try creating a customer file using the requested customer ID
		String outcome = dc.addCustomer(textfield.getText());
		if (outcome.equals("")) applicationStage.setScene(mainScene);
		else newAccountErrorDisplay.setText(outcome);
	}

    // Deposit a specific amount of money into the account of the customer that is logged in
    // on the portal.
	@FXML
    void deposit(ActionEvent event) {
		addMoneyLabel.setTextFill(Color.RED);
		addMoneyLabel.setText("");
		try {
			// Read input: the amount of money to deposit
			int amountToDeposit = depositAmountChoiceBox.getValue();
			
			// If the customer has not logged in
			if (customerInUse == null)
				addMoneyLabel.setText("Please log in first.");
			else {
				// Try to deposit the money into the account.
				customerInUse.addMoney(amountToDeposit);
				addMoneyLabel.setTextFill(Color.BLACK);
				addMoneyLabel.setText("Success!");
				// Update the display of the account balance
				loginSuccessDisplay.setText("Your current balance: " + customerInUse.getBalance());
			}
		}catch(NullPointerException e) {
			// if no amount has been chosen to add.
			addMoneyLabel.setText("Please choose an amount from the choice box to deposit.");
		}
    }

	// When a customer requests to start a wash/drying session.
    @FXML
    void startMachine(ActionEvent event) {
    	machineLabel.setText("");
    	try {
    		// Find the machine that the customer wants to start
    		String machineNumber = machinenumberChoiceBox.getValue();
        	Machine m = dc.getMachine(machineNumber);
        	
        	// Read the wash mode/drying temperature
    		String washMode = washModeChoiceBox.getValue();
    		String temperature = temperatureChoiceBox.getValue();
    		
    		// If the customer wants to start a washing machine but didn't select a wash mode
    		// Display an error message
    		if (washMode == null && machineNumber.contains("Washing"))
    			machineLabel.setText("Please select your wash mode.");
    		
    		// If the customer wants to start a dryer but didn't select the temperature
    		// Display an error message
    		
    		else if (temperature == null && machineNumber.contains("Dryer"))
    			machineLabel.setText("Please select your temperature for the dryer.");
    		
    		// If the customer has not logged in
    		else if (customerInUse == null)
    			machineLabel.setText("Please log in first.");
    		
    		// Check whether the account balance of the customer is greater than the price to
    		// use the machine.
    		else if (customerInUse.getBalance()<=m.getPricePerUse())
    			machineLabel.setText("Insufficient fund, please deposit more money.");
    		
    		// Try starting the machine. Display error message if the machine cannot be started.
    		else {
    			String startOutcome;
    			
    			// Start a washing machine using the input from wash mode choice box
    			// Start a dryer using the input from the temperature choice box 
    			if (machineNumber.contains("Washing"))
    				 startOutcome = m.start(customerInUse.getCustomerID(), washMode);
    			else startOutcome = m.start(customerInUse.getCustomerID(), temperature);
    			
    			// If the machine has been successfully started, deduct the price from the
    			// account of the customer. Update the account balance display in the interface
    			if (startOutcome.isEmpty()) {
    				machineLabel.setText("Payment approved");
    				customerInUse.addMoney(-m.getPricePerUse());
    				loginSuccessDisplay.setText("Your new balance:" + customerInUse.getBalance());
    			// If the machine cannot be started, display the reason in the interface.
    			}else machineLabel.setText(startOutcome);
    		}
    	}catch(NullPointerException e) {
    		// If no machine has been selected to start.
    		machineLabel.setText("Please select a machine frist.");
    	}
    }

    // When a customer requests to end a wash/dryer session.
    @FXML
    void endMachine(ActionEvent event) {
    	machineLabel.setText("");
    	try {
    		// Find the machine that the customer wants to stop
    		String machinenumber = machinenumberChoiceBox.getValue();
        	Machine m = dc.getMachine(machinenumber);
        	
        	// Try ending the session. Display error message if the session cannot be ended.
        	String errormsg = m.pause(customerInUse.getCustomerID());
        	if (!errormsg.isEmpty())
        		machineLabel.setText(errormsg);
        	// if the session has successfully been ended.
        	else machineLabel.setText("Machine is ended. Thanks for using.");
        	
    	}catch(NullPointerException e) {
    		// If no machine has been selected to end
    		machineLabel.setText("Please select a machine frist.");
    	}
    }
    
    // Check the availablity of all machines. Return the names of all available machines.
    @FXML
    void checkAvailable(ActionEvent event) {
    	machineLabel.setText("");
    	String availableMachines = "Available machines:";
    	
    	// Check the availability of each machine one-by-one
    	for (Machine m: dc.getAllMachines())
    		if (!m.getInUse())
    			availableMachines = availableMachines +"\n" + m.getName();
    	machineLabel.setText(availableMachines);
    }

}
