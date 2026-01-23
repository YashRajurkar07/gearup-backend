package com.gearup.services;

import java.util.List;

import com.gearup.dtos.CustomerRegDto;
import com.gearup.entities.Customer;


public interface CustomerService {

	List<Customer> getAllCustomers();

	String registerCustomer(CustomerRegDto customerDetails);

}
