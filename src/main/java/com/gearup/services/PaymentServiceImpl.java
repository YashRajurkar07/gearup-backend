package com.gearup.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.customExceptions.ResourceAlreadyExistsException;
import com.gearup.customExceptions.ResourceNotFoundException;
import com.gearup.entities.Appointment;
import com.gearup.entities.Payment;
import com.gearup.entities.Status;
import com.gearup.repositories.AppointmentRepository;
import com.gearup.repositories.PaymentRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final PaymentRepository paymentRepo;
	private final AppointmentRepository appointmentRepo;
	
//	Get All Payment Details
	@Override
	public List<Payment> getAllPaymentDetails() {

		return paymentRepo.findAll();
	}
	
//	Add Successful Payment Details
	@Override
	public ApiResponse processPaymentSuccess(Long appointmentId, Long transactionId) {

		Appointment appointment = appointmentRepo.findById(appointmentId).orElseThrow(() -> new ResourceNotFoundException("Appointment ID " + appointmentId + " Not Found"));
		Payment payment = appointment.getPayment();
		
		if (appointment.getStatus() == Status.CANCELLED) {
            throw new RuntimeException("Cannot Receive Payment for a CANCELLED Appointment.");
        }
        
        if (payment.getStatus() == Status.SUCCESS) {
            throw new ResourceAlreadyExistsException("Payment Has Already Been Processed For This Appointment.");
        }
        
        payment.setTransactionId(transactionId);
        payment.setStatus(Status.CONFIRMED);
        payment.setPaymentDateTime(LocalDateTime.now());
        
        paymentRepo.save(payment);
        
        appointment.setStatus(Status.CONFIRMED);
        appointmentRepo.save(appointment);
        
        return new ApiResponse("Payment Successful With Transaction ID : "+transactionId+" , Appointment Confirmed!", "Success");
		
	}

}
