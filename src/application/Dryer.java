package application;

public class Dryer extends Machine {
	
	private String temperature;
	
	Dryer(String machineName, double price){
		this.setName(machineName);
		this.setPricePerUse(price);
	}
	
	public void setTemperature(String tempToSet) {
		temperature = tempToSet;
	}
	
	public String getTemperature() {
		return temperature;
	}
	
	@Override
	public String start(String id, String tempToSet) {
		// Check whether this dryer is in service.
		if (!this.getStatus())
			return "Sorry, this machine is under maintenance. Please find another one.";
		// Check whether this dryer is available to use.
		else if (this.getInUse())
			return "Sorry, this machine is in use. Please find another one.";
		else {
			// turn on the dryer
			setTemperature(tempToSet);;
			setInUse(true);
			setCustomerID(id);
			return "";
		}
	}

	@Override
	public String pause(String id) {
		// Check whether this dryer is indeed functioning before trying to stop it.
		if (!this.getInUse())
			return "This machine is not in use.";
		// Check whether the customer that wants to end this session is the customer that's using this machine.
		else if (!id.equals(getCustomerID()))
			return "You cannot stop the machine, because it's serving someone else.";
		else {
			setTemperature("");
			setInUse(false);
			setCustomerID("");
			return "";
		}
	}

	@Override
	public void pause() {
		setInUse(false);
		setCustomerID("");
		temperature = "";
	}

}
