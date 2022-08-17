package application;

public abstract class Machine {
	
	private String name;
	private double pricePerUse;
	// This is the switch of the machine. A machine is deemed to be functioning
	// if and only if inUse = true
	private boolean inUse = false;
	// The status of a machine is defaulted to be true.
	private boolean status = true;
	// Record the ID of the customer that is using this machine
	// empty if this machine is not in use.
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
	
	public void setCustomerID(String id) {
		customerID = id;
	}
	
	// Customer Portal uses this method
	/**
	 * Try starting the machine. If the machine can be started, start the machine and return
	 * empty string; if cannot, return a string message explaining the reason.
	 * @param id: the ID of the customer that requests to use this machine
	 * @param mode: wash mode requested by the customer
	 * @return string error message OR empty string
	 */
	public abstract String start(String id, String mode);
	
	// Customer Portal uses this method
	/**
	 * Try ending the wash/drying session. If machine can be stopped, stop the machine and
	 * return empty string; if cannot, return a string message explaining the reason.
	 * @param id: the ID of the customer that requests the session to be ended.
	 * @return string error message OR empty string
	 */
	public abstract String pause(String id);
	
	// This function is reserved for later use in the Manager Portal
	/**
	 * End the machine immediately without any condition. Can only be called by a manager.
	 */
	public abstract void pause();

}
