package com.gearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gearup.services.GarageService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/garage")
@AllArgsConstructor
public class GarageController {

	private final GarageService garageService;
	
//	Get All Garage Details
	@GetMapping("/getallgarages")
	public ResponseEntity<?> getAllGarages(){
		
		System.out.println("Get All Garages Method Called");
		
		return ResponseEntity.ok(garageService.getAllGarageDetails());
	}
	
}
