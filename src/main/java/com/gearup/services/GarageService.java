package com.gearup.services;

import java.util.List;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.dtos.GarageDto;
import com.gearup.entities.Garage;

public interface GarageService {

	List<Garage> getAllGarageDetails();

	List<Garage> getGaragesByOwnerId(Long ownerId);

	ApiResponse registerNewGarage(GarageDto garageDetails);

	ApiResponse updateGarageDetails(Long garageId, GarageDto garageDetails);

	ApiResponse deleteGarageById(Long garageId);
	
}
