package com.gearup.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.entities.Appointment;
import com.gearup.repositories.AppointmentRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

	private final AppointmentRepository appointmentRepo;
	
//	Get All Appointments Details
	@Override
	public List<Appointment> getAllAppointmentDetails() {

		return appointmentRepo.findAll();
	}

}
