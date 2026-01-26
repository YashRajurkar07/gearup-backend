package com.gearup.services;

import java.util.List;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.entities.Payment;

public interface PaymentService {

	List<Payment> getAllPaymentDetails();

	ApiResponse processPaymentSuccess(Long appointmentId, Long transactionId);
}
