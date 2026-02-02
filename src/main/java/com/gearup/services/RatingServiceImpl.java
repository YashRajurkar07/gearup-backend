package com.gearup.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.customExceptions.InvalidDataException;
import com.gearup.customExceptions.ResourceAlreadyExistsException;
import com.gearup.customExceptions.ResourceNotFoundException;
import com.gearup.dtos.RatingDto;
import com.gearup.entities.Appointment;
import com.gearup.entities.Rating;
import com.gearup.entities.Status;
import com.gearup.repositories.AppointmentRepository;
import com.gearup.repositories.RatingRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {

	private final RatingRepository ratingRepo;
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
		
        return ratingRepo.findAllRatingsByGarageId(garageId);
	}

//	Add New Rating
	@Override
	public ApiResponse addNewRating(RatingDto ratingDetails) {
	
		Appointment appointment = appointmentRepo.findById(ratingDetails.getAppointmentId()).orElseThrow(() -> new ResourceNotFoundException("Appointment ID " + ratingDetails.getAppointmentId() + " Not Found"));
		
		if(appointment.getStatus()!=Status.COMPLETED) {
			throw new InvalidDataException("You Can Only Rate an Appointment That Has Been Completed");
		}
		
		if (appointment.getRating() != null) {
            throw new ResourceAlreadyExistsException("You Have Already Rated This Appointment");
        }
		
		Rating newRating = mapper.map(ratingDetails, Rating.class);
		Rating savedRating = ratingRepo.save(newRating);
		
		appointment.setRating(savedRating);
		appointmentRepo.save(appointment);
		
		return new ApiResponse("Rating Submitted Successfully With Rating ID : "+savedRating.getId(), "Success");
		
	}
	
//	Get Average Rating For A Particular Garage
	@Override
	public Double getAverageRatingForGarage(Long garageId) {
		
		Double score = ratingRepo.getAverageRatingByGarageId(garageId);
		
		return (score != null) ? score : 0.0;
	}

}
