package com.akash.springboot.project.carServiceApp.packg.customer;

import java.util.List;

public interface CustomerDAO {

	public List<Customer> getAllInfo();
	
	public int deleteCustomerAppointment(String email);
	
	public void addCustomerAppointment(Customer cc);
	
	public void updateCustomerAppointment(Customer cc);
	
	public Customer getAppointmentDetails(String emailId);
	
	
}
