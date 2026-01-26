package com.gearup.services;

import java.util.List;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.dtos.GarageDto;
import com.gearup.dtos.GarageStatsDto;
import com.gearup.entities.Garage;

public interface GarageService {

	List<Garage> getAllGarageDetails();

	List<Garage> getGaragesByOwnerId(Long ownerId);

	List<Garage> getGarageByArea(String area);
	
	List<Garage> getGarageByNameOrCity(String name);
	
	List<String> getAllAreasOfGarages();
	
	ApiResponse registerNewGarage(GarageDto garageDetails);

	ApiResponse updateGarageDetails(Long garageId, GarageDto garageDetails);

	ApiResponse deleteGarageById(Long garageId);

	GarageStatsDto getGarageStatistics(Long garageId);

}
