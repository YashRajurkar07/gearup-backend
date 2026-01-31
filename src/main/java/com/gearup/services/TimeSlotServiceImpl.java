package com.gearup.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.customExceptions.ResourceNotFoundException;
import com.gearup.dtos.TimeSlotDto;
import com.gearup.entities.Garage;
import com.gearup.entities.TimeSlot;
import com.gearup.repositories.GarageRepository;
import com.gearup.repositories.TimeSlotRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class TimeSlotServiceImpl implements TimeSlotService {

	private final TimeSlotRepository timeSlotRepo;
	private final GarageRepository garageRepo;
	private final ModelMapper mapper;
	
//	Get All Time Slots 
	@Override
	public List<TimeSlot> getAllTimeSlotDetails() {

		return timeSlotRepo.findAll();
	}

//	Get All Time Slots By Garage ID
	@Override
	public List<TimeSlot> getAllSlotsByGarageId(Long garageId) {
		
		Garage garage = garageRepo.findById(garageId).orElseThrow(()-> new ResourceNotFoundException("Garage with ID : "+garageId+" Not Found"));
		return timeSlotRepo.findByGarage(garage);
	}

//	Get All Available Time Slots
	@Override
	public List<TimeSlot> getAvailableSlotsByGarage(Long garageId) {
		
		Garage garage = garageRepo.findById(garageId).orElseThrow(() -> new ResourceNotFoundException("Garage with ID : "+garageId+" Not Found"));
        
        return timeSlotRepo.findByGarageAndIsBookedFalse(garage);
	}
	
//	Add New Time Slot 
	@Override
	public ApiResponse addNewTimeSlot(TimeSlotDto slotDetails) {
		
		Garage garage = garageRepo.findById(slotDetails.getGarageId()).orElseThrow(() -> new ResourceNotFoundException("Garage ID Not Found"));

        if (slotDetails.getStartTime().isAfter(slotDetails.getEndTime())) {
            throw new IllegalArgumentException("Start Time cannot be after End Time");
        }

        TimeSlot timeSlot = mapper.map(slotDetails, TimeSlot.class);
        timeSlot.setGarage(garage);
        timeSlot.setBooked(false);

        timeSlotRepo.save(timeSlot);

        return new ApiResponse("Time Slot Added Successfully", "Success");
	}

//	Update Time Slot Details
	@Override
	public ApiResponse updateTimeSlotDetails(Long slotId, TimeSlotDto slotDetails) {
		
		TimeSlot existingSlot = timeSlotRepo.findById(slotId).orElseThrow(() -> new ResourceNotFoundException("Time Slot ID " + slotId + " Not Found"));

        if (existingSlot.isBooked()) {
            throw new RuntimeException("Cannot update a slot that is already booked by a customer.");
        }

        mapper.map(slotDetails, existingSlot);

        timeSlotRepo.save(existingSlot);

        return new ApiResponse("Time Slot with ID : "+slotId+" Updated Successfully", "Success");
	}

//	Delete Time Slot Permanently
	@Override
	public ApiResponse deleteTimeSlot(Long slotId) {
		
		TimeSlot existingSlot = timeSlotRepo.findById(slotId).orElseThrow(() -> new ResourceNotFoundException("Time Slot ID " + slotId + " Not Found"));

        if (existingSlot.isBooked()) {
            throw new RuntimeException("Cannot Delete This slot. It is Currently Booked By a Customer. Cancel the Appointment First.");
        }

        timeSlotRepo.delete(existingSlot);

        return new ApiResponse("Time Slot with ID : "+slotId+" Updated Successfully", "Success");
	}

}
