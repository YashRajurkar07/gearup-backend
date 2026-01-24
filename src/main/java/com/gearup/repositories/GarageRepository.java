package com.gearup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearup.entities.Garage;

public interface GarageRepository extends JpaRepository<Garage, Long> {

}
