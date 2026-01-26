package com.gearup.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.customExceptions.ResourceNotFoundException;
import com.gearup.dtos.GarageDto;
import com.gearup.entities.Garage;
import com.gearup.entities.Owner;
import com.gearup.repositories.GarageRepository;
import com.gearup.repositories.OwnerRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class GarageServiceImpl implements GarageService {

	private final GarageRepository garageRepo;
	private final OwnerRepository ownerRepo;
	private final ModelMapper mapper;
	
//	Get All Garage Details
	@Override
	public List<Garage> getAllGarageDetails() {

		return garageRepo.findAll();
	}

//	Get Garage by Owner ID
	@Override
	public List<Garage> getGaragesByOwnerId(Long ownerId) {
		Owner owner = ownerRepo.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner ID " + ownerId + " Not Found"));
        return garageRepo.findByOwner(owner); 
	}
	
//	Register New Garage
	@Override
	public ApiResponse registerNewGarage(GarageDto garageDetails) {
		// TODO Auto-generated method stub
		return null;
	}

//	Update Garage Details
	@Override
	public ApiResponse updateGarageDetails(Long garageId, GarageDto garageDetails) {
		
		Garage existingGarage = garageRepo.findById(garageId).orElseThrow(() -> new ResourceNotFoundException("Garage with ID " + garageId + " Does Not Exist"));
        
        mapper.map(garageDetails, existingGarage);
        
        garageRepo.save(existingGarage);

        return new ApiResponse("Garage Details Updated Successfully for ID: " + garageId, "Success");
	}

//	Soft Delete Garage Details
	@Override
	public ApiResponse deleteGarageById(Long garageId) {
		Garage existingGarage = garageRepo.findById(garageId).orElseThrow(() -> new ResourceNotFoundException("Garage with ID " + garageId + " Does Not Exist"));
        
        existingGarage.setActive(false);
        garageRepo.save(existingGarage);
        
        return new ApiResponse("Garage with ID: " + garageId + " Deleted Successfully", "Success");
	}

}
