package application;

public abstract class Machine {
	
	private String name;
	private double pricePerUse;
	private boolean inUse = false;
	private boolean status = true;
	private String customerID = "";
	
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
	
	public void setName(String nameToSet) {
		name = nameToSet;
	}
	
	public void setInUse(boolean inUseToSet) {
		inUse = inUseToSet;
	}
	
	public abstract String start(String id, String mode);
	
	public abstract String pause();
	
	public abstract String pause(String password);

}
