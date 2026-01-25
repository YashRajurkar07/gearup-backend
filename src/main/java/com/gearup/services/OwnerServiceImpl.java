package com.gearup.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.customExceptions.ResourceAlreadyExistsException;
import com.gearup.customExceptions.ResourceNotFoundException;
import com.gearup.dtos.OwnerDto;
import com.gearup.dtos.UserDto;
import com.gearup.entities.Owner;
import com.gearup.entities.UserRole;
import com.gearup.repositories.OwnerRepository;
import com.gearup.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {

	private final OwnerRepository ownerRepo;
	private final UserRepository userRepo;
	private final ModelMapper mapper;
	
//	Get All Owners Details
	@Override
	public List<Owner> getAllOwnerDetails() {

		return ownerRepo.findAll();
		
	}

//	Register New Owner
	@Override
	public ApiResponse registerOwner(OwnerDto ownerDetails) {

		if(userRepo.existsByEmail(ownerDetails.getUserDetails().getEmail())) {
			throw new ResourceAlreadyExistsException("Email Already Exists, Try Another Email Id");
		}
		
		Owner newOwner = mapper.map(ownerDetails, Owner.class);
		newOwner.getUserDetails().setRole(UserRole.ROLE_OWNER);
		newOwner.getUserDetails().setActive(true);
		
		Owner persistantData = ownerRepo.save(newOwner);
		return new ApiResponse("Successfully Added Owner with ID : "+persistantData.getId(), "Success");
	}

//	Update Owner Details
	@Override
	public ApiResponse updateOwner(Long oId, OwnerDto ownerDetails) {
		
		Owner existingOwner = ownerRepo.findById(oId).orElseThrow(()-> new ResourceNotFoundException("Owner with Id "+oId+" Does Not Exists"));
		
		mapper.map(ownerDetails, existingOwner);
		ownerRepo.save(existingOwner);
		
		return new ApiResponse("Data Updated Successfully for Owner with Id : "+oId, "Success");
	}

//	Soft Delete Owner Record
	@Override
	public ApiResponse deleteOwnerById(Long oId) {

		Owner existingOwner = ownerRepo.findById(oId).orElseThrow(()-> new ResourceNotFoundException("Owner with Id "+oId+" Does Not Exists"));
		
		existingOwner.getUserDetails().setActive(false);
		ownerRepo.save(existingOwner);
		
		return new ApiResponse("Owner With ID : "+oId+" Deleted Successfully", "Success");
	}

}
