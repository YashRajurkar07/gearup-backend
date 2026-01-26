package com.gearup.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.entities.Payment;
import com.gearup.repositories.PaymentRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final PaymentRepository paymentRepo;
	
//	Get All Payment Details
	@Override
	public List<Payment> getAllPaymentDetails() {

		return paymentRepo.findAll();
	}

}
