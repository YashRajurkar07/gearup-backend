package com.gearup.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.customExceptions.ResourceAlreadyExistsException;
import com.gearup.dtos.CustomerRegDto;
import com.gearup.entities.Customer;
import com.gearup.entities.UserRole;
import com.gearup.repositories.CustomerRepository;
import com.gearup.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepo;
	private final UserRepository userRepo;
	private final ModelMapper mapper;
	
//	Get All Customer Details
	@Override
	public List<Customer> getAllCustomerDetails() {
		
		return customerRepo.findAll();
	}
	
//	Post New Customer Details
	@Override
	public ApiResponse registerCustomer(CustomerRegDto customerDetails){
		
		if(userRepo.existsByEmail(customerDetails.getUserDetails().getEmail())) {
			throw new ResourceAlreadyExistsException("Email Already Exists, Try Another Email Id");
		}
		
		Customer entity = mapper.map(customerDetails, Customer.class);
		
		entity.getUserDetails().setRole(UserRole.ROLE_CUSTOMER);
		
		Customer persistantData = customerRepo.save(entity);
		
		return new ApiResponse("Successfully Added Customer with ID : "+persistantData.getId(), "Success");
	}

//	Soft Delete Customer Details
	@Override
	public ApiResponse deleteCustomerById(Long id) {
		
		Customer cust = customerRepo.findById(id).orElseThrow(() -> new ResourceAlreadyExistsException("ID Does Not Exists"));
		
		//---------------------------------------------------
		
		return new ApiResponse("Customer With ID : "+id+" Deleted Successfully", "Success");
		
	}

}
