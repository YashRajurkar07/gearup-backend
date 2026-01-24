package com.gearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gearup.services.TimeSlotService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/timeslot")
@AllArgsConstructor
public class TimeSlotController {

	private final TimeSlotService timeSlotService;
	
//	Get All Time Slots Details
	@GetMapping("/getalltimeslots")
	public ResponseEntity<?> getAllTimeSlots(){
		
		System.out.println("Get All Time Slots Method Called");
		return ResponseEntity.ok(timeSlotService.getAllTimeSlotDetails());
	}
}
