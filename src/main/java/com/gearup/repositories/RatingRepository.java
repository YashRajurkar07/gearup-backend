package com.gearup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gearup.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {

	@Query("select r from Appointment a join a.rating r where a.garage.id = :garageId")
	List<Rating> findAllRatingsByGarageId(Long garageId);
	
	@Query("select avg(r.score) from Appointment a join a.rating r where a.garage.id = :garageId")
	Double getAverageRatingByGarageId(Long garageId);
	
}
