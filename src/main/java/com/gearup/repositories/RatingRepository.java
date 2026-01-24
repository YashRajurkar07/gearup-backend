package com.gearup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearup.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {

}
