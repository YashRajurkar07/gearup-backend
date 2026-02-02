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
import com.gearup.dtos.CustomerRegDto;
import com.gearup.dtos.OwnerDto;
import com.gearup.services.AdminService;
import com.gearup.services.CustomerService;
import com.gearup.services.GarageService;
import com.gearup.services.OwnerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

	private final AdminService adminService;
	private final CustomerService customerService;
	private final GarageService garageService;
	private final OwnerService ownerService;

//	Get All Admin Details
	@GetMapping("/getalladmins")
	public ResponseEntity<?> getAllAdmins() {

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

//	Get All Customer Details
	@GetMapping("/getallcustomers")
	public ResponseEntity<?> getAllCustomers() {
		System.out.println("Get All Customers Method Called");
		return ResponseEntity.ok(customerService.getAllCustomerDetails());
	}

//	Get Customer By Customer Id
	@GetMapping("/getcustomerbyid/{cid}")
	public ResponseEntity<?> getCustomerById(@PathVariable Long cid) {
		System.out.println("Get Customer By Id Method Called");

		return ResponseEntity.ok(customerService.getCustomerByCustomerId(cid));
	}

//	Update Customer Details
	@PutMapping("/updatecustomerdetails/{cid}")
	public ResponseEntity<?> updateCustomerDetails(@PathVariable Long cid,
			@Valid @RequestBody CustomerRegDto customerDetails) {

		System.out.println("Update Customer Method Called");

		return ResponseEntity.ok(customerService.updateCustomer(cid, customerDetails));
	}

//	Soft Delete Customer Details
	@DeleteMapping("/deletecustomer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {

		System.out.println("Delete Customer Method Called");

		return ResponseEntity.ok(customerService.deleteCustomerById(id));
	}

//	Get All Garage Details
	@GetMapping("/getallgarages")
	public ResponseEntity<?> getAllGarages() {

		System.out.println("Get All Garages Method Called");

		return ResponseEntity.ok(garageService.getAllGarageDetails());
	}

	// Get All Owner Details
	@GetMapping("/getallowners")
	public ResponseEntity<?> getAllOwners() {

		System.out.println("Get All Owner Method Called");
		return ResponseEntity.ok(ownerService.getAllOwnerDetails());
	}
	
//	Get Owner By Owner ID
	@GetMapping("/getownerbyid/:oid")
	public ResponseEntity<?> getOwnerById(@PathVariable Long oid){
		
		return ResponseEntity.ok(ownerService.getOwnerByOwnerId(oid));
	}

	// Update Owner Details
	@PutMapping("/updateownerdetails/{oid}")
	public ResponseEntity<?> updateOwnerDetails(@PathVariable Long oid, @Valid @RequestBody OwnerDto ownerDetails) {

		System.out.println("Update Owner Method Called");
		return ResponseEntity.ok(ownerService.updateOwner(oid, ownerDetails));
	}

	// Soft Delete Owner
	@DeleteMapping("/deleteowner/{oid}")
	public ResponseEntity<?> deleteOwner(@PathVariable Long oid) {

		System.out.println("Delete Owner Method Called");
		return ResponseEntity.ok(ownerService.deleteOwnerById(oid));
	}
}
