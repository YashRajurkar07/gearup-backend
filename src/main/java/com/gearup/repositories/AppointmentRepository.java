package com.gearup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearup.entities.Appointment;
import com.gearup.entities.Customer;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByCustomer(Customer customer);

}
