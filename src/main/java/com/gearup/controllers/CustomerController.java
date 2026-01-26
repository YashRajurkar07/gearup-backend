package com.gearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gearup.dtos.CustomerRegDto;
import com.gearup.services.CustomerService;

import jakarta.validation.Valid;
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
		 return ResponseEntity.ok(customerService.getAllCustomerDetails());
	}
	
//	Get Customer By Customer Id
	@GetMapping("/getcustomerbyid/{cid}")
	public ResponseEntity<?> getCustomerById(@PathVariable Long cid){
		System.out.println("Get Customer By Id Method Called");
		
		return ResponseEntity.ok(customerService.getCustomerByCustomerId(cid));
	}
	
//	Post New Customer Details
	@PostMapping("/register")
	public ResponseEntity<?> registerNewCustomer(@Valid @RequestBody CustomerRegDto customerDetails){
		
		System.out.println("Register Customer Method Called");
		
		return ResponseEntity.ok(customerService.registerCustomer(customerDetails));
		
	}
	
//	Update Customer Details
	@PutMapping("/upatecustomerdetails/{cid}")
	public ResponseEntity<?> updateCustomerDetails(@PathVariable Long cid, @Valid @RequestBody CustomerRegDto customerDetails){
		
		System.out.println("Update Customer Method Called");
		
		return ResponseEntity.ok(customerService.updateCustomer(cid, customerDetails));
	}
	
//	Soft Delete Customer Details
	@DeleteMapping("/deletemyaccount/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
		
		System.out.println("Delete Customer Method Called");
		
		return ResponseEntity.ok(customerService.deleteCustomerById(id));
		
	}

}
