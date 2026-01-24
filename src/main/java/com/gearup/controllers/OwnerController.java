package com.gearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gearup.services.OwnerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/owner")
@AllArgsConstructor
public class OwnerController {

	private final OwnerService ownerService;
	
//	Get All Owners Details
	@GetMapping("/getallowners")
	public ResponseEntity<?> getAllOwners(){
		
		System.out.println("Get All Owner Method Called");
		return ResponseEntity.ok(ownerService.getAllOwnerDetails());
		
	}
	
}
