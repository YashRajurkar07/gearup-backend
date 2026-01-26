package com.gearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gearup.dtos.GarageDto;
import com.gearup.services.GarageService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/garage")
@AllArgsConstructor
public class GarageController {

	private final GarageService garageService;

//	Get All Garage Details
	@GetMapping("/getallgarages")
	public ResponseEntity<?> getAllGarages() {

		System.out.println("Get All Garages Method Called");

		return ResponseEntity.ok(garageService.getAllGarageDetails());
	}

//	Get Garages for a specific Owner
	@GetMapping("/garagebyowner/{ownerId}")
	public ResponseEntity<?> getGaragesByOwner(@PathVariable Long ownerId) {
		System.out.println("Get Owner Garages Method Called");
		return ResponseEntity.ok(garageService.getGaragesByOwnerId(ownerId));
	}

//	Get Garage by Area
	@GetMapping("/garagebyarea")
	public ResponseEntity<?> getGarageByArea(@RequestBody String area) {

		return ResponseEntity.ok(garageService.getGarageByArea(area));
	}

//	Search Garage By Name 
	@GetMapping("/searchgarages")
	public ResponseEntity<?> getGarageByNameCity(@RequestBody String name) {

		return ResponseEntity.ok(garageService.getGarageByNameOrCity(name));
	}

//	Get All Areas of The Garages
	@GetMapping("/getallareas")
	public ResponseEntity<?> getAllAreas(){
		
		return ResponseEntity.ok(garageService.getAllAreasOfGarages());
	}
	
//	Register New Garage
	@PostMapping("/registergarage")
	public ResponseEntity<?> registerGarage(@Valid @RequestBody GarageDto garageDetails) {
		System.out.println("Register Garage Method Called");
		return ResponseEntity.ok(garageService.registerNewGarage(garageDetails));
	}

//    Update Garage Details
	@PutMapping("/updategarage/{garageId}")
	public ResponseEntity<?> updateGarage(@PathVariable Long garageId, @Valid @RequestBody GarageDto garageDetails) {
		System.out.println("Update Garage Method Called");
		return ResponseEntity.ok(garageService.updateGarageDetails(garageId, garageDetails));
	}

//    Soft Delete Garage
	@DeleteMapping("/deletegarage/{garageId}")
	public ResponseEntity<?> deleteGarage(@PathVariable Long garageId) {
		System.out.println("Delete Garage Method Called");
		return ResponseEntity.ok(garageService.deleteGarageById(garageId));
	}
	
//	Get Garage Current Statistics 
	@GetMapping("/garagestatistics/{garageId}")
	public ResponseEntity<?> getGarageStats(@PathVariable Long garageId){
		
		return ResponseEntity.ok(garageService.getGarageStatistics(garageId));
	}
	
}
