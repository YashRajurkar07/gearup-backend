package com.gearup.services;

import java.util.List;

import com.gearup.customAPIResponse.ApiResponse;
import com.gearup.dtos.RatingDto;
import com.gearup.entities.Rating;

public interface RatingService {

	List<Rating> getAllRatingDetails();

	List<Rating> getRatingsByGarageId(Long garageId);

	ApiResponse addNewRating(RatingDto ratingDetails);

}
