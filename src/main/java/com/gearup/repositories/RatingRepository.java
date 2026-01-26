package com.gearup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearup.entities.Garage;
import com.gearup.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {

	List<Rating> findByGarage(Garage garage);

}
