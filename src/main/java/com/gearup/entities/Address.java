package com.gearup.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {
	
	private String city;
	private String state;
	private String country;
	private String area;
	private int zipCode;
	
}
