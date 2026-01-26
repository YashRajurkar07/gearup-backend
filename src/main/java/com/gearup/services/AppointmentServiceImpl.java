package com.gearup.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.customExceptions.ResourceNotFoundException;
import com.gearup.dtos.AppointmentDto;
import com.gearup.entities.Appointment;
import com.gearup.entities.Customer;
import com.gearup.entities.Status;
import com.gearup.repositories.AppointmentRepository;
import com.gearup.repositories.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

	private final AppointmentRepository appointmentRepo;
	private final CustomerRepository customerRepo;
	private final ModelMapper mapper;
	
//	Get All Appointments Details
	@Override
	public List<Appointment> getAllAppointmentDetails() {

		return appointmentRepo.findAll();
	}

//	Get Appointments By Customer ID
	@Override
	public List<Appointment> getAppointmentsByCustomerId(Long customerId) {
		
		Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer ID " + customerId + " not found"));
		
		return appointmentRepo.findByCustomer(customer); 
	}

//	Book Appointment Details
	@Override
	public ApiResponse bookNewAppointment(AppointmentDto appointmentDetails) {
		// TODO Auto-generated method stub
		return null;
	}

//	Update Appointment Details
	@Override
	public ApiResponse updateAppointment(Long apptId, AppointmentDto appointmentDetails) {
		// TODO Auto-generated method stub
		return null;
	}

//	Cancel Appointment
	@Override
	public ApiResponse cancelAppointment(Long apptId) {
		
		Appointment existingAppt = appointmentRepo.findById(apptId).orElseThrow(() -> new ResourceNotFoundException("Appointment ID " + apptId + " Not Found"));

        existingAppt.setStatus(Status.CANCELLED);
        
        appointmentRepo.save(existingAppt);

        return new ApiResponse("Appointment " + apptId + " Cancelled", "Success");
	}

}
