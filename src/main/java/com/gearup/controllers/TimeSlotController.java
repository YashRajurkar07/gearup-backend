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

import com.gearup.dtos.TimeSlotDto;
import com.gearup.services.TimeSlotService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/timeslot")
@AllArgsConstructor
public class TimeSlotController {

	private final TimeSlotService timeSlotService;
	
//	Get All Time Slots Details
	@GetMapping("/getalltimeslots")
	public ResponseEntity<?> getAllTimeSlots(){
		
		System.out.println("Get All Time Slots Method Called");
		return ResponseEntity.ok(timeSlotService.getAllTimeSlotDetails());
	}
	
//	Get All Slots for a Garage 
    @GetMapping("/timeslotsforgarage/{garageId}")
    public ResponseEntity<?> getSlotsByGarage(@PathVariable Long garageId) {
    	
        System.out.println("Get Garage Slots Method Called");
        return ResponseEntity.ok(timeSlotService.getAllSlotsByGarageId(garageId));
    }
    
//    Get Only Available Slots
    @GetMapping("/timeslotsforgarage/{garageId}/available")
    public ResponseEntity<?> getAvailableSlots(@PathVariable Long garageId) {
        System.out.println("Get Available Slots Method Called");
        return ResponseEntity.ok(timeSlotService.getAvailableSlotsByGarage(garageId));
    }
    
//    Add New Time Slot
    @PostMapping("/addtimeslot")
    public ResponseEntity<?> addTimeSlot(@RequestBody TimeSlotDto slotDetails) {
    	
        System.out.println("Add Time Slot Method Called");
        return ResponseEntity.ok(timeSlotService.addNewTimeSlot(slotDetails));
    }
    
//    Update Time Slot Details
    @PutMapping("/updatetimeslot/{slotId}")
    public ResponseEntity<?> updateTimeSlot(@PathVariable Long slotId, @RequestBody TimeSlotDto slotDetails) {
    	
        System.out.println("Update Time Slot Method Called");
        return ResponseEntity.ok(timeSlotService.updateTimeSlotDetails(slotId, slotDetails));
    }
    
//    Soft Delete TimeSlot
    @DeleteMapping("/deletetimeslot/{slotId}")
    public ResponseEntity<?> deleteTimeSlot(@PathVariable Long slotId) {
    	
        System.out.println("Delete Time Slot Method Called");
        return ResponseEntity.ok(timeSlotService.deleteTimeSlot(slotId));
    }
}
