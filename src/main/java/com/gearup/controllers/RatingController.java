package com.gearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gearup.services.RatingService;

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
}
