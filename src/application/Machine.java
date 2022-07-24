package application;

public abstract class Machine {
	
	protected String name;
	protected double pricePerUse;
	protected boolean inUse = false;
	protected boolean status = true;
	protected String customerID = "";
	
	public String getName() {
		return name;
	}
	
	public double getPricePerUse() {
		return pricePerUse;
	}
	
	public boolean getInUse() {
		return inUse;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public String getCustomerID() {
		return customerID;
	}
	
	public void setPricePerUse(double newPrice) {
		pricePerUse = newPrice;
	}
	
	public void setStatus(boolean newStatus) {
		status = newStatus;
	}
	
	public abstract String start(String id, String mode);
	
	public abstract String pause();
	
	public abstract String pause(String password);

}
