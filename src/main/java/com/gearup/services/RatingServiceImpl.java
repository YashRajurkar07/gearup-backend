package com.gearup.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gearup.entities.Rating;
import com.gearup.repositories.RatingRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {

	private final RatingRepository ratingRepo;
	
//	Get All Rating Details
	@Override
	public List<Rating> getAllRatingDetails() {

		return ratingRepo.findAll();
	}

}
