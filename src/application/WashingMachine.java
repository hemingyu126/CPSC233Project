package application;

public class WashingMachine extends Machine{
	
	private String washMode = "";
	
	WashingMachine(String machineName, double price){
		this.setName(machineName);;
		this.setPricePerUse(price);;
	}
	
	public String getWashMode() {
		return washMode;
	}
	
	public void setWashMode(String toSet) {
		washMode = toSet;
	}

	@Override
	public String start(String id, String mode) {
		// Check whether this washing machine is in service.
		if (!this.getStatus())
			return "Sorry, this machine is under maintenance. Please find another one.";
		// Check whether this washing machine is available to use.
		else if (this.getInUse())
			return "Sorry, this machine is in use. Please find another one.";
		else {
			// Start the machine
			setWashMode(mode);
			setInUse(true);
			setCustomerID(id);
			return "";
		}
	}

	@Override
	public String pause(String id) {
		// Check whether this washing machine is indeed functioning before trying to stop it.
		if (!this.getInUse())
			return "This machine is not in use.";
		// Check whether the customer that wants to end this session is the customer that's using this machine.
		else if (!id.equals(getCustomerID()))
			return "You cannot stop the machine, because it's serving someone else.";
		else {
			// End the session
			setWashMode("");
			setInUse(false);
			setCustomerID("");
			return "";
		}
	}

	@Override
	public void pause() {
		setWashMode("");
		setInUse(false);
		setCustomerID("");
	}

}
