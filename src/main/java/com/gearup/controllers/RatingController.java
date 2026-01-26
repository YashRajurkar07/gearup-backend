package com.gearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gearup.dtos.RatingDto;
import com.gearup.services.RatingService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/rating")
@AllArgsConstructor
public class RatingController {

	private final RatingService ratingService;
	
//	Get All Rating Details
	@GetMapping("/getallratings")
	public ResponseEntity<?> getAllRatings(){
		
		System.out.println("Get All Ratings Method Called");
		return ResponseEntity.ok(ratingService.getAllRatingDetails());
	}
	
//	Get Ratings for a Specific Garage 
    @GetMapping("/garagerating/{garageId}")
    public ResponseEntity<?> getRatingsByGarage(@PathVariable Long garageId) {
        System.out.println("Get Garage Ratings Method Called");
        return ResponseEntity.ok(ratingService.getRatingsByGarageId(garageId));
    }
    
//    Add a New Rating
    @PostMapping("/addrating")
    public ResponseEntity<?> addRating(@Valid @RequestBody RatingDto ratingDetails) {
        System.out.println("Add Rating Method Called");
        return ResponseEntity.ok(ratingService.addNewRating(ratingDetails));
    }
   
}
