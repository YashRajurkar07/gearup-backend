package com.gearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gearup.services.AppointmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/appointment")
@AllArgsConstructor
public class AppointmentController {

	private final AppointmentService appointmentService;
	
//	Get All Appointments Details
	@GetMapping("/getallappointments")
	public ResponseEntity<?> getAllAppointments(){
		
		System.out.println("Get All Appointments Method Called");
		return ResponseEntity.ok(appointmentService.getAllAppointmentDetails());
	}
	
	
}
