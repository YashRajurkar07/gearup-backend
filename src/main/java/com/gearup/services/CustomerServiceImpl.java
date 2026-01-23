package com.gearup.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.dtos.CustomerRegDto;
import com.gearup.entities.Customer;
import com.gearup.entities.UserRole;
import com.gearup.repositories.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepo;
	private ModelMapper mapper;
	
	@Override
	public List<Customer> getAllCustomers() {
		
		return customerRepo.findAll();
	}
	
	@Override
	public String registerCustomer(CustomerRegDto customerDetails) {
		
		Customer entity = mapper.map(customerDetails, Customer.class);
		
		entity.getUserDetails().setRole(UserRole.ROLE_CUSTOMER);
		
		Customer persistantData = customerRepo.save(entity);
		return "Success"+persistantData.getId();
	}

}
