package com.gearup.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.entities.TimeSlot;
import com.gearup.repositories.TimeSlotRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class TimeSlotServiceImpl implements TimeSlotService {

	private final TimeSlotRepository timeSlotRepo;
	
	@Override
	public List<TimeSlot> getAllTimeSlotDetails() {

		return timeSlotRepo.findAll();
	}

}
