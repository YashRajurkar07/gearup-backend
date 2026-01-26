package com.gearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
