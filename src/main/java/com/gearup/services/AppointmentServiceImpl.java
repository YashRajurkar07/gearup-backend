package com.gearup.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.customExceptions.InvalidDataException;
import com.gearup.customExceptions.ResourceAlreadyExistsException;
import com.gearup.customExceptions.ResourceNotFoundException;
import com.gearup.dtos.AppointmentBookingDto;
import com.gearup.entities.Appointment;
import com.gearup.entities.Customer;
import com.gearup.entities.Payment;
import com.gearup.entities.Status;
import com.gearup.entities.TimeSlot;
import com.gearup.repositories.AppointmentRepository;
import com.gearup.repositories.CustomerRepository;
import com.gearup.repositories.PaymentRepository;
import com.gearup.repositories.TimeSlotRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

	private final AppointmentRepository appointmentRepo;
	private final CustomerRepository customerRepo;
	private final TimeSlotRepository timeSlotRepo;
	private final PaymentRepository paymentRepo;
	
//	Get All Appointments Details
	@Override
	public List<Appointment> getAllAppointmentDetails() {

		return appointmentRepo.findAll();
	}

//	Get Appointments By Customer ID
	@Override
	public List<Appointment> getAppointmentsByCustomerId(Long customerId) {
		
		Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer ID " + customerId + " Not Found"));
		
		return appointmentRepo.findByCustomer(customer); 
	}
	
//	Get Appointments By Garage ID
	@Override
	public List<Appointment> getAppointmentsByGarageId(Long gid) {
		
		return appointmentRepo.findByGarageId(gid);
	}

//	Get Upcoming Appointments For Customer (Pending and Confirmed)
	@Override
	public List<Appointment> getUpcomingAppointments(Long customerId){
		
		List<Status> statuses = Arrays.asList(Status.PENDING, Status.CONFIRMED);
		
		return appointmentRepo.findByCustomer_IdAndStatusIn(customerId, statuses);
	}

//	Get History of Appointments For Customer (Completed and Cancelled)
	@Override
	public List<Appointment> getAppointmentHistory(Long custId) {
		
		return appointmentRepo.findByCustomer_IdAndStatusIn(custId, Arrays.asList(Status.COMPLETED, Status.CANCELLED));
	}

//	Book Appointment Details
	@Override
	public ApiResponse bookNewAppointment(AppointmentBookingDto appointmentDetails) {

		Customer customer = customerRepo.findById(appointmentDetails.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Customer ID " + appointmentDetails.getCustomerId() + " Not Found"));
		TimeSlot timeSlot = timeSlotRepo.findById(appointmentDetails.getTimeSlotId()).orElseThrow(() -> new ResourceNotFoundException("Time Slot ID " + appointmentDetails.getTimeSlotId() + " Not Found"));
		if(timeSlot.isBooked()) {
			throw new ResourceAlreadyExistsException("Time Slot Already Booked for This Time Period, Try Different Time Slot");
		}
		
		timeSlot.setBooked(true);
		timeSlotRepo.save(timeSlot);
		
		Payment newPayment = new Payment();
        newPayment.setAmount(500.00); 
        newPayment.setStatus(Status.PENDING);
        newPayment.setTransactionMode(appointmentDetails.getPaymentMode());
        newPayment.setTransactionId(System.currentTimeMillis()); // Temporary Useless ID, Need to Replace with Gateway ID later
        newPayment.setPaymentDateTime(java.time.LocalDateTime.now());
        
        Payment paidData = paymentRepo.save(newPayment);
        
        Appointment newAppointment = new Appointment();
        newAppointment.setCustomer(customer);
        newAppointment.setGarage(timeSlot.getGarage());
        newAppointment.setTimeslot(timeSlot);
        newAppointment.setPayment(paidData);
        newAppointment.setAppointmentDate(timeSlot.getStartTime().toLocalDate());
        newAppointment.setStatus(Status.PENDING);
		
        Appointment persistantData = appointmentRepo.save(newAppointment);

        return new ApiResponse("Appointment Booked Successfully! ID: " + persistantData.getId(), "Success");
	}
	
//	Mark The Appointment as Completed
	@Override
	public ApiResponse markAppointmentCompleted(Long appointmentId) {
		
		Appointment existingAppointment = appointmentRepo.findById(appointmentId).orElseThrow(() -> new ResourceNotFoundException("Appointment ID " + appointmentId + " Not Found"));
		
		if(existingAppointment.getStatus() != Status.CONFIRMED) {
			throw new RuntimeException("Cannot Mark This Appointment as Completed. Current Status: " + existingAppointment.getStatus());
		}
		
		existingAppointment.setStatus(Status.COMPLETED);
		appointmentRepo.save(existingAppointment);
		
		return new ApiResponse("Service Marked as Completed Successfully", "Success");
	}

//	Update Appointment Details
	@Override
	public ApiResponse updateAppointment(Long apptId, AppointmentBookingDto appointmentDetails) {

		Appointment existingAppointment = appointmentRepo.findById(apptId).orElseThrow(() -> new ResourceNotFoundException("Appointment ID " + apptId + " Not Found"));
		
		if(existingAppointment.getStatus()==Status.CANCELLED || existingAppointment.getStatus()==Status.COMPLETED) {
			throw new InvalidDataException("Cannot Update an Appointment That Is " + existingAppointment.getStatus());
		}
		
		Long oldSlotId = existingAppointment.getTimeslot().getId();
        Long newSlotId = appointmentDetails.getTimeSlotId();
        
        if(!oldSlotId.equals(newSlotId)) {
        	
        	TimeSlot newSlot = timeSlotRepo.findById(newSlotId).orElseThrow(() -> new ResourceNotFoundException("New Time Slot ID " + newSlotId + " not found"));

            if (newSlot.isBooked()) {
                throw new ResourceAlreadyExistsException("The Selected New Time Slot is Already Booked. Please Choose Another Time Slot");
            }
            if (!newSlot.getGarage().getId().equals(existingAppointment.getGarage().getId())) {
                throw new RuntimeException("You Cannot Reschedule To a Slot In a Different Garage");
            }
            
            TimeSlot oldSlot = existingAppointment.getTimeslot();
            oldSlot.setBooked(false);
            timeSlotRepo.save(oldSlot);
            
            newSlot.setBooked(true);
            timeSlotRepo.save(newSlot);
            
            existingAppointment.setTimeslot(newSlot);
            existingAppointment.setAppointmentDate(newSlot.getStartTime().toLocalDate());
            
        }
        
       appointmentRepo.save(existingAppointment);
        
       return new ApiResponse("Appointment Rescheduled Successfully", "Success");
		
	}

//	Cancel Appointment
	@Override
	public ApiResponse cancelAppointment(Long apptId) {
		
		Appointment existingAppt = appointmentRepo.findById(apptId).orElseThrow(() -> new ResourceNotFoundException("Appointment ID " + apptId + " Not Found"));

		if(existingAppt.getStatus()==Status.COMPLETED) {
			throw new RuntimeException("Cannot Cancel a Completed Appointment.");
		}
        
        TimeSlot existingSlot = existingAppt.getTimeslot();
        existingSlot.setBooked(false);
        timeSlotRepo.save(existingSlot);
        
        existingAppt.setStatus(Status.CANCELLED);
        appointmentRepo.save(existingAppt);

        return new ApiResponse("Appointment " + apptId + " Cancelled", "Success");
	}

}
