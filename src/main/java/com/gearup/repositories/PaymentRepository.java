package com.gearup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearup.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
