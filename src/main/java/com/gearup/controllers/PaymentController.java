package com.gearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gearup.services.PaymentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;
	
//	Get All Payment Details
	@GetMapping("/getallpayments")
	public ResponseEntity<?> getAllPayments(){
		
		System.out.println("Get All Payment Method Called");
		return ResponseEntity.ok(paymentService.getAllPaymentDetails());
		
	}
	
//	Post New Successful Payment Details
	@PostMapping("/success")
	public ResponseEntity<?> addNewPaymentDetails(@RequestParam Long appointmentId, @RequestParam Long transactionId){
		
		System.out.println("Payment Callback Received for Appointment ID: " + appointmentId);
		return ResponseEntity.ok(paymentService.processPaymentSuccess(appointmentId, transactionId));
		
	}
	
}
