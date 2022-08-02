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
			return "Sorry, this machine is not available to use.";
		else if (this.getInUse())
			return "This machine is currently in use. Please find another one.";
		else {
			this.setTemperature(tempToSet);;
			this.setInUse(true);
			return "";
		}
	}

	@Override
	public String pause() {
		this.setInUse(false);
		temperature = "";
		return "";
	}

	@Override
	public String pause(String password) {
		return pause();
	}

}
