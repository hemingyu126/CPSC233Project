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
		if (!this.getStatus())
			return "Sorry, this machine is under maintenance. Please find another one.";
		else if (this.getInUse())
			return "Sorry, this machine is in use. Please find another one.";
		else {
			setWashMode(mode);
			setInUse(true);
			setCustomerID(id);
			return "";
		}
	}

	@Override
	public String pause(String id) {
		if (!this.getInUse())
			return "This machine is not in use.";
		else if (!id.equals(getCustomerID()))
			return "You cannot stop the machine, because it's serving someone else.";
		else {
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
