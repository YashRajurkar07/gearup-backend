package com.gearup.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gearup.dtos.AdminDto;
import com.gearup.services.AdminService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

	private final AdminService adminService;

//	Get All Admin Details
	@GetMapping("/getalladmins")
	public ResponseEntity<?> getAllAdmins(){
		
		System.out.println("Get All Admins Called");
		return ResponseEntity.ok(adminService.getAllAdminDetails());
		
	}
	
//	Register New Admin
    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody AdminDto adminDetails) {
    	
        System.out.println("Register Admin Method Called");
        return ResponseEntity.ok(adminService.registerNewAdmin(adminDetails));
    }
	
//    Update Admin Details
    @PutMapping("/updateadmindetails/{adminId}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long adminId, @Valid @RequestBody AdminDto adminDetails) {
    	
        System.out.println("Update Admin Method Called");
        return ResponseEntity.ok(adminService.updateAdminDetails(adminId, adminDetails));
    }
    
//    Soft Delete Admin
    @DeleteMapping("/delete/{adminId}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminId) {
    	
        System.out.println("Delete Admin Method Called");
        return ResponseEntity.ok(adminService.deleteAdminById(adminId));
    }
    
//	Mark Garage Owner as Verified or Not
    @PutMapping("/verifyowner/{ownerId}")
    public ResponseEntity<?> verifyOwner(@PathVariable Long ownerId, @RequestParam boolean status) {
        
        return ResponseEntity.ok(adminService.verifyGarageOwner(ownerId, status));
    }
}
