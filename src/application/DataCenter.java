package application;

import java.util.ArrayList;

public class DataCenter {
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private ArrayList<Machine> machines = new ArrayList<Machine>();
	
	// Initial set-up
	DataCenter(){
		customers = new ArrayList<Customer>();
		machines = new ArrayList<Machine>();
		WashingMachine machine1 = new WashingMachine("Washing Machine 1", 1.75);
		WashingMachine machine2 = new WashingMachine("Washing Machine 2", 1.75);
		WashingMachine machine3 = new WashingMachine("Washing Machine 3", 1.75);
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
	
	/**
	 * Find a customer in our database by their ID. Return NULL if no customer of
	 * that ID can be found.
	 * @param customerID
	 * @return a customer with that ID OR null
	 */
	public Customer getCustomer(String customerID){
		for (Customer c: customers) {
			if (c.getCustomerID().equals(customerID))
				return c;
		}
		return null;
	}
	
	/**
	 * Find a machine by its name in our database. Return NULL if no machine of that
	 * name can be found.
	 * @param name
	 * @return a machine of that name OR null
	 */
	public Machine getMachine(String name){
		for (Machine m: machines) {
			if (m.getName().equals(name))
				return m;
		}
		return null;
	}
	
	public ArrayList<Machine> getAllMachines(){
		return machines;
	}
	
	/**
	 * Consumes an ID proposed by the customer and check whether the ID is valid.
	 * If valid, create an account for the customer in our database using that ID, return ""
	 * If not valid, return a string message explaining why it's not valid.
	 * @param requestedID
	 * @return a string error message, empty if the requested ID is valid
	 */
	public String addCustomer(String requestedID) {
		// ID can consists of digits. No other characters allowed
		for (char c: requestedID.toCharArray())
			if (!Character.isDigit(c)) {
				if(c == ' ') {
					return "Customer ID should be integers and cannot contain 'space'.";
				}else return "Customer ID should be integers and cannot contain: " + c;
			}
		// ID must be of length 4
		if (requestedID.length()!=4)
			return "Customer ID must be 4 digits. The ID you requested is "+requestedID.length()+ " digits";
		// ID cannot already be occupied by other customers.
		for (Customer c: customers)
			if(c.getCustomerID().equals(requestedID))
				return "This customer ID already exists. Please try another one.";
		// If the proposed ID is valid, create a new account of that ID and return empty string
		Customer toAdd = new Customer(requestedID);
		customers.add(toAdd);
		return "";
	}
}
