package com.akash.springboot.project.carServiceApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akash.springboot.project.carServiceApp.packg.customer.Customer;
import com.akash.springboot.project.carServiceApp.packg.customer.CustomerDAO;
@Service
public class CustomerServiceImpl implements CustomerService {

	public CustomerDAO customerDAO;
	
	@Autowired
	public CustomerServiceImpl(CustomerDAO customer) {
		customerDAO=customer;
	}
	
	
	@Override
	@Transactional
	public List<Customer> getAllInfo() {
		
		return customerDAO.getAllInfo();
	}

	@Override
	@Transactional
	public int deleteCustomerAppointment(String email) {
		return customerDAO.deleteCustomerAppointment(email) ;
	}

	@Override
	@Transactional
	public void addCustomerAppointment(Customer cc) {
		customerDAO.addCustomerAppointment(cc);
		return ;

	}

	@Override
	@Transactional
	public void updateCustomerAppointment(Customer cc) {
		customerDAO.updateCustomerAppointment(cc);

	}

	@Override
	@Transactional
	public Customer getAppointmentDetails(String emailId) {	
		return customerDAO.getAppointmentDetails(emailId);
	}

}
