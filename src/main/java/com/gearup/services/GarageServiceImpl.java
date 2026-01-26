package com.gearup.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.customExceptions.ResourceNotFoundException;
import com.gearup.dtos.GarageDto;
import com.gearup.dtos.GarageStatsDto;
import com.gearup.entities.Garage;
import com.gearup.entities.Owner;
import com.gearup.repositories.AppointmentRepository;
import com.gearup.repositories.GarageRepository;
import com.gearup.repositories.OwnerRepository;
import com.gearup.repositories.RatingRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class GarageServiceImpl implements GarageService {

	private final GarageRepository garageRepo;
	private final OwnerRepository ownerRepo;
	private final AppointmentRepository appointmentRepo;
	private final RatingRepository ratingRepo;
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
	
//	Get Garage Details By Area
	@Override
	public List<Garage> getGarageByArea(String area) {
		
		List<Garage> garageList = garageRepo.findByAddressAreaIgnoreCase(area);
		
		if(garageList.isEmpty()) {
			throw new ResourceNotFoundException("Garage in Area " + area + " Not Found"); 
		}
		
		return garageList;
	}
	
//	Get Garage By Garage Name
	@Override
	public List<Garage> getGarageByNameOrCity(String name) {
		
		List<Garage> garageList = garageRepo.searchGarages(name);
		
		if(garageList.isEmpty()) {
			throw new ResourceNotFoundException("No Garages Were Found"); 
		}
		
		return garageList;
	}
	
//	Get All Areas of The Garages
	@Override
	public List<String> getAllAreasOfGarages() {
		
		return garageRepo.findDistinctAreas();
	}
	
//	Register New Garage
	@Override
	public ApiResponse registerNewGarage(GarageDto garageDetails) {
		
		Garage newGarage = mapper.map(garageDetails, Garage.class);
		Owner owner = ownerRepo.findById(garageDetails.getOwnerId()).orElseThrow(()->new ResourceNotFoundException("Owner with ID " + garageDetails.getOwnerId() + " Does Not Exist"));
		
		newGarage.setOwner(owner);
		
		Garage persistantEntity = garageRepo.save(newGarage);
		
		return new ApiResponse("New Garage Registered SuccessFully, Garage ID : "+persistantEntity.getId(), "Success");
	}

//	Update Garage Details
	@Override
	public ApiResponse updateGarageDetails(Long garageId, GarageDto garageDetails) {
		
		Garage existingGarage = garageRepo.findById(garageId).orElseThrow(() -> new ResourceNotFoundException("Garage with ID " + garageId + " Does Not Exist"));
		Owner owner = ownerRepo.findById(garageDetails.getOwnerId()).orElseThrow(() -> new ResourceNotFoundException("Owner with ID " + garageDetails.getOwnerId() + " Does Not Exist"));
        
        mapper.map(garageDetails, existingGarage);
        existingGarage.setOwner(owner);
        
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

//	Get Garage Current Statistics 
	@Override
	public GarageStatsDto getGarageStatistics(Long garageId) {
		
		long todaysAppointments = appointmentRepo.getTodaysAppointments(garageId);
		long pendingrequests = appointmentRepo.getPendingAppointments(garageId);
		Double totalRevenue = appointmentRepo.calculateTotalRevenue(garageId);
		
		if(totalRevenue == null) {
			totalRevenue = 0.0;
		}
		
		Double averageRating = ratingRepo.getAverageRatingByGarageId(garageId);
		
		if(averageRating == null) {
			averageRating = 0.0;
		}
		
		return new GarageStatsDto(todaysAppointments, pendingrequests, totalRevenue, averageRating);
	}
}
