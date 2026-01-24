package com.gearup.controllers;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gearup.services.AdminService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final ModelMapper modelMapper;

	private final AdminService adminService;

//	Get All Admin Details
	@GetMapping("/getalladmins")
	public ResponseEntity<?> getAllAdmins(){
		
		System.out.println("Get All Admins Called");
		return ResponseEntity.ok(adminService.getAllAdminDetails());
		
	}
	
}
