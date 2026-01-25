package com.gearup.services;

import java.util.List;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.dtos.AppointmentDto;
import com.gearup.entities.Appointment;

public interface AppointmentService {

	List<Appointment> getAllAppointmentDetails();

	List<Appointment> getAppointmentsByCustomerId(Long custId);

	ApiResponse bookNewAppointment(AppointmentDto appointmentDetails);

	ApiResponse updateAppointment(Long apptId, AppointmentDto appointmentDetails);

	ApiResponse cancelAppointment(Long apptId);

}
