package com.akash.springboot.project.carServiceApp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akash.springboot.project.carServiceApp.packg.customer.Customer;
import com.akash.springboot.project.carServiceApp.packg.customer.CustomerDAO;
import com.akash.springboot.project.carServiceApp.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

public CustomerService customerService;
	@Autowired
	public CustomerRestController(CustomerService cd) {
		customerService=cd;
	}
	
	@GetMapping("/customers")
	public List<Customer> getAllInfo() {
		return customerService.getAllInfo();
	}
	
	
	@GetMapping("/customers/getInfo/{customerId}")
	public Customer getCustomerInfo(@PathVariable String customerId) {
		return customerService.getAppointmentDetails(customerId);
	}
	
	
	@DeleteMapping("/deleteCustomerAppointment/{email}")
	public void deleteAppointment(@PathVariable String email) {
		customerService.deleteCustomerAppointment(email);
		return ;
		
	}
	
	@PostMapping("/customers")
	public Customer addAppointment(@RequestBody Customer c) {
		c.setId(0);
		customerService.addCustomerAppointment(c);
		return c ;
	}
	
	@PutMapping("/customers")
	public void updateAppointment(@RequestBody Customer c) {
		customerService.updateCustomerAppointment(c);
	}
	
	
}
