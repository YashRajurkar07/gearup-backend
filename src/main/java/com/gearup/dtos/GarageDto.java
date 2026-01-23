package com.gearup.dtos;

import java.time.LocalDate;

import com.gearup.entities.Address;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GarageDto {

	private String garageName;
	
	private Address address;
	
	private String garagePhone;
	
	private int totalMechanics;
	
	private LocalDate openingTime;
	
	private LocalDate closingTime;
}
