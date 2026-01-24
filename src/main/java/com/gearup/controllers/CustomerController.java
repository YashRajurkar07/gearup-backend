package com.gearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gearup.dtos.CustomerRegDto;
import com.gearup.services.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;

//	Get All Customer Details
	@GetMapping("/getallcustomers")
	public ResponseEntity<?> getAllCustomers(){
		 System.out.println("Get All Customers Method Called");
		 return ResponseEntity.ok(customerService.getAllCustomers());
	}
	
//	Post New Customer Details
	@PostMapping("/register")
	public ResponseEntity<?> registerData(@RequestBody CustomerRegDto customerDetails){
		
		System.out.println("Register Customer Method Called");
		
		return ResponseEntity.ok(customerService.registerCustomer(customerDetails));
		
	}
	
	
//	@GetMapping("/getmessage")
//	public String getTitle() {
//		return "Success";
//	}
}
