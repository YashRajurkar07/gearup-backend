package com.gearup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearup.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
