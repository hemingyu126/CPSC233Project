package application;

public class Customer {
	
	private String customerID;
	private double balance = 0.0;
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
	
	public void setBalance(double amount) {
		balance = amount;
	}
	
	public void setStatus(boolean newStatus) {
		status = newStatus;
	}
	
	public void addMoney(double amountToAdd) {
		if (status) balance += amountToAdd;
	}
	
	public boolean equals(Customer c){
		if (c.getCustomerID().equals(customerID)) return true;
		else return false;
	}

}
