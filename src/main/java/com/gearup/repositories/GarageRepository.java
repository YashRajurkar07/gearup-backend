package com.gearup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gearup.entities.Garage;
import com.gearup.entities.Owner;

public interface GarageRepository extends JpaRepository<Garage, Long> {

	List<Garage> findByOwner(Owner owner);

	List<Garage> findByAddressAreaIgnoreCase(String area);

	@Query("select g  from Garage g where g.garageName like %:name% or g.address.city like %:name%")
	List<Garage> searchGarages(String name);

	@Query("select distinct g.address.area from Garage g")
	List<String> findDistinctAreas();
	
}
