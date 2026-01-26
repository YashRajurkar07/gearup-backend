package com.gearup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearup.entities.Garage;
import com.gearup.entities.TimeSlot;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
	
	List<TimeSlot> findByGarage(Garage garage);
	
	List<TimeSlot> findByGarageAndIsBookedFalse(Garage garage);

}
