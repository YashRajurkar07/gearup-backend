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

import com.gearup.dtos.OwnerDto;
import com.gearup.services.GarageService;
import com.gearup.services.OwnerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/owner")
@AllArgsConstructor
public class OwnerController {

	private final OwnerService ownerService;
	private final GarageService garageService;

	// Get All Owner Details
//	@GetMapping("/getallowners")
//	public ResponseEntity<?> getAllOwners() {
//
//		System.out.println("Get All Owner Method Called");
//		return ResponseEntity.ok(ownerService.getAllOwnerDetails());
//	}

//	Get Owner By Owner ID
	@GetMapping("/getownerbyid/:oid")
	public ResponseEntity<?> getOwnerById(@PathVariable Long oid){
		
		return ResponseEntity.ok(ownerService.getOwnerByOwnerId(oid));
	}
	
	// Register New Owner
	@PostMapping("/register")
	public ResponseEntity<?> registerNewOwner(@Valid @RequestBody OwnerDto ownerDetails) {

		System.out.println("Register Owner Method Called");
		return ResponseEntity.ok(ownerService.registerOwner(ownerDetails));
	}

	// Update Owner Details
	@PutMapping("/updateownerdetails/{oid}")
	public ResponseEntity<?> updateOwnerDetails(@PathVariable Long oid, @Valid @RequestBody OwnerDto ownerDetails) {

		System.out.println("Update Owner Method Called");
		return ResponseEntity.ok(ownerService.updateOwner(oid, ownerDetails));
	}

	// Soft Delete Owner
	@DeleteMapping("/deletemyaccount/{oid}")
	public ResponseEntity<?> deleteOwner(@PathVariable Long oid) {

		System.out.println("Delete Owner Method Called");
		return ResponseEntity.ok(ownerService.deleteOwnerById(oid));
	}
	
//	Get Garage By Garage ID
	@GetMapping("/garagebyid/{garageId}")
	public ResponseEntity<?> getGarageById(@PathVariable Long garageId) {
		
		return ResponseEntity.ok(garageService.getGarageByGarageId(garageId));
		
	}
}
