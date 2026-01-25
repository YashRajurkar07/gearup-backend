package com.gearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gearup.dtos.OwnerRegDto;
import com.gearup.services.OwnerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/owner")
@AllArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    // Get All Owner Details
    @GetMapping("/getallowners")
    public ResponseEntity<?> getAllOwners() {

        System.out.println("Get All Owner Method Called");
        return ResponseEntity.ok(ownerService.getAllOwnerDetails());
    }

    // Register New Owner
    @PostMapping("/register")
    public ResponseEntity<?> registerOwner(
            @RequestBody OwnerRegDto ownerDetails) {

        System.out.println("Register Owner Method Called");
        return ResponseEntity.ok(ownerService.registerOwner(ownerDetails));
    }

    // Update Owner Details
    @PutMapping("/updateownerdetails")
    public ResponseEntity<?> updateOwnerDetails(
            @RequestBody OwnerRegDto ownerDetails) {

        System.out.println("Update Owner Method Called");
        return ResponseEntity.ok(ownerService.updateOwner(ownerDetails));
    }

    // Soft Delete Owner
    @DeleteMapping("/deletemyaccount/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable Long id) {

        System.out.println("Delete Owner Method Called");
        return ResponseEntity.ok(ownerService.deleteOwnerById(id));
    }
}
