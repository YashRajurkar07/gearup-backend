package com.gearup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearup.entities.Garage;
import com.gearup.entities.Owner;

public interface GarageRepository extends JpaRepository<Garage, Long> {

	List<Garage> findByOwner(Owner owner);

}
