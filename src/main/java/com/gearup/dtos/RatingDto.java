package com.gearup.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDto {

	@Length(max = 100)
	private String comment;

	@DecimalMin(value = "0.5")
	@DecimalMax(value = "5.0")
	private double score;
	
}
