package com.gearup.services;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.dtos.CustomerRegDto;
import com.gearup.entities.Customer;


public interface CustomerService {

	List<Customer> getAllCustomerDetails();

	Customer getCustomerByCustomerId(Long cid);
	
	ApiResponse registerCustomer(CustomerRegDto customerDetails);

	ApiResponse deleteCustomerById(Long id);

	ApiResponse updateCustomer(Long id, CustomerRegDto customerDetails);


}
