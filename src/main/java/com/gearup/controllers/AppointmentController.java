package com.gearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gearup.dtos.AppointmentDto;
import com.gearup.services.AppointmentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/appointment")
@AllArgsConstructor
public class AppointmentController {

	private final AppointmentService appointmentService;

//	Get All Appointments Details
	@GetMapping("/getallappointments")
	public ResponseEntity<?> getAllAppointments() {

		System.out.println("Get All Appointments Method Called");
		return ResponseEntity.ok(appointmentService.getAllAppointmentDetails());
	}

//	 Get Appointments by Customer ID
	@GetMapping("/customer/{custId}")
	public ResponseEntity<?> getAppointmentsByCustomer(@PathVariable Long custId) {

		System.out.println("Get Customer Appointments By ID Method Called");
		return ResponseEntity.ok(appointmentService.getAppointmentsByCustomerId(custId));
	}

//	 Book New Appointment
	@PostMapping("/bookappointment")
	public ResponseEntity<?> bookAppointment(@Valid @RequestBody AppointmentDto appointmentDetails) {

		System.out.println("Book Appointment Method Called");
		return ResponseEntity.ok(appointmentService.bookNewAppointment(appointmentDetails));
	}

//	 Update/Reschedule Appointment
	@PutMapping("/updateappointment/{apptId}")
	public ResponseEntity<?> updateAppointment(@PathVariable Long apptId, @Valid @RequestBody AppointmentDto appointmentDetails) {

		System.out.println("Update Appointment Method Called");
		return ResponseEntity.ok(appointmentService.updateAppointment(apptId, appointmentDetails));
	}

//	 Cancel Appointment (Soft Delete)
	@DeleteMapping("/cancelAppointment/{apptId}")
	public ResponseEntity<?> cancelAppointment(@PathVariable Long apptId) {

		System.out.println("Cancel Appointment Method Called");
		return ResponseEntity.ok(appointmentService.cancelAppointment(apptId));
	}

}
