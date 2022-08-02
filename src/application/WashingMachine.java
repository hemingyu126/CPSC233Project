package application;

public class WashingMachine extends Machine{
	
	private String washMode = "";
	private String pausePassword;
	
	WashingMachine(String machineName, double price, String pwd){
		this.setName(machineName);;
		this.setPricePerUse(price);;
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
		if (!this.getStatus())
			return "Sorry, this machine is not available to use.";
		else if (this.getInUse())
			return "This machine is currently in use. Please find another one.";
		else {
			setWashMode(mode);
			this.setInUse(true);;
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
			setWashMode("");
			this.setInUse(false);;
			return "";
		}else return "Invalid Password";
	}

}
