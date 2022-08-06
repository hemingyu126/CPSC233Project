package application;

import java.util.ArrayList;

public class DataCenter {
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private ArrayList<Machine> machines = new ArrayList<Machine>();
	
	DataCenter(){
		customers = new ArrayList<Customer>();
		machines = new ArrayList<Machine>();
		WashingMachine machine1 = new WashingMachine("Washing Machine 1", 1.75, "wm1");
		WashingMachine machine2 = new WashingMachine("Washing Machine 2", 1.75, "wm2");
		WashingMachine machine3 = new WashingMachine("Washing Machine 3", 1.75, "wm3");
		Dryer dryer1 = new Dryer("Dryer 1", 1.25);
		Dryer dryer2 = new Dryer("Dryer 2", 1.25);
		Dryer dryer3 = new Dryer("Dryer 3", 1.25);
		machines.add(machine1);
		machines.add(machine2);
		machines.add(machine3);
		machines.add(dryer1);
		machines.add(dryer2);
		machines.add(dryer3);
	}
	
	public Customer getCustomer(String customerID){
		for (Customer c: customers) {
			if (c.getCustomerID().equals(customerID))
				return c;
		}
		return null;
	}
	
	public Machine getMachine(String name){
		for (Machine m: machines) {
			if (m.getName().equals(name))
				return m;
		}
		return null;
	}
	
	public String addCustomer(String requestedID) {
		for (char c: requestedID.toCharArray())
			if (!Character.isDigit(c))
				return "Customer ID should an integer and cannot contain:" + c;
		if (requestedID.length()!=4)
			return "Customer ID must be 4 digits. The ID you requested is "+requestedID.length()+ " digits";
		Customer toAdd = new Customer(requestedID);
		customers.add(toAdd);
		return "";
	}
}
