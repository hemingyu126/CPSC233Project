package application;

public class Customer {
	
	private String customerID;
	private double balance = 0.0;
	// The status of a customer is defaulted to be true
	private boolean status = true;
	
	Customer(String id){
		customerID = id;
	}
	
	public String getCustomerID() {
		return customerID;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean newStatus) {
		status = newStatus;
	}
	
	/**
	 * Add a specific amount of money to the customer's account.
	 * @param amountToAdd
	 */
	public void addMoney(double amountToAdd) {
		if (status) balance += amountToAdd;
	}
	
	/**
	 * Overwrite the equals method from the Object class to compare customers
	 * Two customers are the same if they have the same customer id.
	 * @param Customer c
	 * @return true if two customers are the same; false otherwise.
	 */
	public boolean equals(Customer c){
		if (c.getCustomerID().equals(customerID)) return true;
		else return false;
	}

}
