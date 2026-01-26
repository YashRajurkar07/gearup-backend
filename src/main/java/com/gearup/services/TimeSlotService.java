package com.gearup.services;

import java.util.List;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.dtos.TimeSlotDto;
import com.gearup.entities.TimeSlot;

public interface TimeSlotService {

	List<TimeSlot> getAllTimeSlotDetails();

	List<TimeSlot> getAllSlotsByGarageId(Long garageId);

	List<TimeSlot> getAvailableSlotsByGarage(Long garageId);

	ApiResponse addNewTimeSlot(TimeSlotDto slotDetails);

	ApiResponse updateTimeSlotDetails(Long slotId, TimeSlotDto slotDetails);

	ApiResponse deleteTimeSlot(Long slotId);

}
