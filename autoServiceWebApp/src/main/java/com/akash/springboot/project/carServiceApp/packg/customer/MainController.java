package com.akash.springboot.project.carServiceApp.packg.customer;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akash.springboot.project.carServiceApp.service.CustomerService;



@Controller
public class MainController {
	
	public CustomerService customerService;
	@Autowired
	public MainController(CustomerService cd) {
		customerService=cd;
	}
	
	
	@GetMapping("/")
	public String indexPage(Model model) {

		model.addAttribute("name", "");
		return "index";
	}
	
	@GetMapping("/about.html")
	public String aboutPage(Model model) {
		
		return "about";
	}
	
	@GetMapping("/index.html")
	public String indexReturnPage(Model model) {

		model.addAttribute("name", "");
		return "index";
	}
	
	@GetMapping("/allAppointments.html")
	public String allAppointments(Model model) {
		List<Customer>customerList=customerService.getAllInfo();
		model.addAttribute("customerList",customerList);
		return "allAppointments";
	}
	
	@GetMapping("/bookings.html")
	public String bookingsPage() {
		return "bookings";
	}
	
	@GetMapping("/appointmentDetails.html")
	public String appointmentDetailsPage(Model model) {
		Customer customer=new Customer();
		model.addAttribute("customer", customer);
		return "appointmentDetails";
	}
	
	@GetMapping("/individualAppointment")
	public String individualAppointment(@ModelAttribute("customer") Customer theCustomer,Model model) {
		String email=theCustomer.getEmail();
		Customer c=customerService.getAppointmentDetails(email);
		if(c==null) return "errorPage";
		model.addAttribute("customer", c);
		return "individualAppointment";
	}
	
	@GetMapping("/addForm.html")
	public String addFormPage(Model model) {
		Customer customer=new Customer();
		model.addAttribute("customer", customer);
		
		return "addForm";
	}
	
	@PostMapping("/saved")
public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		String email=theCustomer.getEmail();
		Customer cc=customerService.getAppointmentDetails(email);
		int flag=0;
		if(cc!=null) {
			//theCustomer.setId(cc.getId());
			flag=1;
		}
		String tt=LocalDateTime.now()
			       .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		
		
		String[] te=tt.split(" ");
		String curr=(te[1]).substring(0,8);
		Time t=Time.valueOf(curr);
		theCustomer.setBookingTime(t);
		
		// save the employee
		if(flag==1) {
		 cc.setFirstName(theCustomer.getFirstName());	
		 cc.setLastName(theCustomer.getLastName());
		 cc.setEmail(theCustomer.getEmail());
		 cc.setPhoneNumber(theCustomer.getPhoneNumber());
		 cc.setServiceType(theCustomer.getServiceType());
		 cc.setBookingDate(theCustomer.getBookingDate());
		 cc.setBookingTime(theCustomer.getBookingTime());
		 customerService.addCustomerAppointment(cc);
		}else {
		customerService.addCustomerAppointment(theCustomer);
		}
		// use a redirect to prevent duplicate submissions
		return "formSaveConfirmation";
	}
	
	
	@GetMapping("/deleteForm.html")
	public String deleteForm(Model model) {
		//Add the customer Object
		Customer customer=new Customer();
		model.addAttribute("customer", customer);
		
		return "deleteForm";
	}
	
	@GetMapping("/deleted")
public String deleteCustomer(@ModelAttribute("customer") Customer theCustomer) {
		//Get email details and check if such object exists in DB or not
		String temp=theCustomer.getEmail();
		System.out.println("Email:::::::::      "+temp);
		Customer cc=customerService.getAppointmentDetails(temp);
		if(cc==null) return "errorPage";
		customerService.deleteCustomerAppointment(temp);
		return "formDeleteConfirmation";
	}
	
	@GetMapping("/updateForm.html")
	public String updateCustomer(Model model) {
		Customer customer=new Customer();
		model.addAttribute("customer", customer);
		return "updateForm";
	}
	
	@GetMapping("/updateDetails")
	public String updateDetails(@ModelAttribute("customer") Customer theCustomer,Model model) {
		String email=theCustomer.getEmail();
		Customer c=customerService.getAppointmentDetails(email);
		if(c==null) return "errorPage";
		model.addAttribute("customer", c);
		return "updateDetails";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@ModelAttribute("customer") Customer theCustomer,Model model) {
		String email=theCustomer.getEmail();
		Customer cc=customerService.getAppointmentDetails(email);
		model.addAttribute("customer", cc);
		return "addForm";
	}
	
	
	
}
