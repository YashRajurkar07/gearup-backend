package com.gearup.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.customExceptions.ResourceNotFoundException;
import com.gearup.dtos.RatingDto;
import com.gearup.entities.Garage;
import com.gearup.entities.Rating;
import com.gearup.repositories.AppointmentRepository;
import com.gearup.repositories.GarageRepository;
import com.gearup.repositories.RatingRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {

	private final RatingRepository ratingRepo;
	private final GarageRepository garageRepo;
	private final AppointmentRepository appointmentRepo;
	private final ModelMapper mapper;
	
//	Get All Rating Details
	@Override
	public List<Rating> getAllRatingDetails() {

		return ratingRepo.findAll();
	}

//	Get Rating By GarageId
	@Override
	public List<Rating> getRatingsByGarageId(Long garageId) {
		
		Garage garage = garageRepo.findById(garageId).orElseThrow(() -> new ResourceNotFoundException("Garage ID " + garageId + " Not Found"));
        
        return ratingRepo.findByGarage(garage);
	}

//	Add New Rating
	@Override
	public ApiResponse addNewRating(RatingDto ratingDetails) {
		// TODO Auto-generated method stub
//		-------------------------------------------------------------------------
		return null;
	}

}
