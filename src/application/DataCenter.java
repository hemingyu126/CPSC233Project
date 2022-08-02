package application;

import java.util.ArrayList;

public class DataCenter {
	ArrayList<Customer> customers = new ArrayList<Customer>();
	ArrayList<Machine> machines = new ArrayList<Machine>();
	
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


}
