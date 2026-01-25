package com.gearup.dtos;

import java.time.LocalTime;

import com.gearup.entities.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GarageDto {

	private String garageName;
	
	private Address address;
	
	private String garagePhone;
	
	private int totalMechanics;
	
	private LocalTime openingTime;
	
	private LocalTime closingTime;
	
	private boolean isActive;
}
