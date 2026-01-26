package com.gearup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gearup.entities.Appointment;
import com.gearup.entities.Customer;
import com.gearup.entities.Status;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByCustomer(Customer customer);
	
	List<Appointment> findByGarageIdAndRatingIsNotNull(Long garageId);
	
	List<Appointment> findByGarageId(Long garageId);
	
	List<Appointment> findByCustomer_IdAndStatusIn(Long customerId, List<Status> statuses);

	@Query("select count(a) from Appointment a where a.garage.id = :garageId and a.appointmentDate = current_date")
	long getTodaysAppointments(Long garageId);
	
	@Query("select count(a) from Appointment a where a.garage.id = :garageId and a.status = 'PENDING'")
	long getPendingAppointments(Long garageId);
	
	@Query("select sum(p.amount) from Appointment a join a.payment p where a.garage.id = :garageId and p.status = 'SUCCESS'")
	double calculateTotalRevenue(Long garageId);
}
