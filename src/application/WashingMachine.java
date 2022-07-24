package application;

public class WashingMachine extends Machine{
	
	private String washMode = "";
	private String pausePassword;
	
	WashingMachine(String machineName, double price, String pwd){
		super.name = machineName;
		super.pricePerUse = price;
		pausePassword = pwd;
	}
	
	public String getWashMode() {
		return washMode;
	}
	
	public void setWashMode(String toSet) {
		washMode = toSet;
	}

	@Override
	public String start(String id, String mode) {
		if (!this.status)
			return "Sorry, this machine is not available to use.";
		else if (this.inUse)
			return "This machine is currently in use. Please find another one.";
		else {
			setWashMode(mode);
			super.inUse = true;
			return "";
		}
	}

	@Override
	public String pause() {
		return "Wash cannot be paused. Please see a staff member for assistance.";
	}

	@Override
	public String pause(String password) {
		if (password!=null && password.equals(pausePassword)) {
			washMode = "";
			super.inUse = false;
			return "";
		}else return "Invalid Password";
	}

}
