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
		if (!this.getStatus())
			return "Sorry, this machine is under maintenance. Please find another one.";
		else if (this.getInUse())
			return "Sorry, this machine is in use. Please find another one.";
		else {
			setTemperature(tempToSet);;
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
