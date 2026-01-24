package com.gearup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearup.entities.TimeSlot;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

}
