package com.gearup.services;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.dtos.AppointmentBookingDto;
import com.gearup.entities.Appointment;

import jakarta.validation.Valid;

public interface AppointmentService {

	List<Appointment> getAllAppointmentDetails();

	List<Appointment> getAppointmentsByCustomerId(Long custId);

	List<Appointment> getAppointmentsByGarageId(Long gid);
	
	ApiResponse bookNewAppointment(AppointmentBookingDto appointmentDetails);

	ApiResponse updateAppointment(Long apptId, AppointmentBookingDto appointmentDetails);

	ApiResponse cancelAppointment(Long apptId);

	List<Appointment> getUpcomingAppointments(Long customerId);

	List<Appointment> getAppointmentHistory(Long custId);

	ApiResponse markAppointmentCompleted(Long appointmentId);

	ApiResponse updateAppointmentStatus(Long apptId, @Valid AppointmentBookingDto appointmentDetails);

}
