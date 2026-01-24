package com.gearup.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.entities.Garage;
import com.gearup.repositories.GarageRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class GarageServiceImpl implements GarageService {

	private GarageRepository garageRepo;
	
//	Get All Garage Details
	@Override
	public List<Garage> getAllGarageDetails() {

		return garageRepo.findAll();
	}

}
