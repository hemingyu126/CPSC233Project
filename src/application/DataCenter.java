package application;

import java.util.ArrayList;

public class DataCenter {
	private ArrayList<Customer> customers;
	private ArrayList<Machine> machines;
	
	DataCenter(){
		customers = new ArrayList<Customer>();
		machines = new ArrayList<Machine>();
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
	
	public void addCustomer(Customer newCustomter) {
		boolean toAdd = true;
		for (Customer c: customers)
			if (c.getCustomerID().equals(newCustomter.getCustomerID()))
				toAdd = false;
		if (toAdd)
			customers.add(newCustomter);
	}


}