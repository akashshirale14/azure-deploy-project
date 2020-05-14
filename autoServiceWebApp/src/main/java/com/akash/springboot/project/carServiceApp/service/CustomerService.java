package com.akash.springboot.project.carServiceApp.service;

import java.util.List;

import com.akash.springboot.project.carServiceApp.packg.customer.Customer;

public interface CustomerService {

      public List<Customer> getAllInfo();
	
	public int deleteCustomerAppointment(String email);
	
	public void addCustomerAppointment(Customer cc);
	
	public void updateCustomerAppointment(Customer cc);
	
	public Customer getAppointmentDetails(String emailId);
	
}
