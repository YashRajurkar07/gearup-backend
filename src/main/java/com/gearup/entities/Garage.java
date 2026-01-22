package com.gearup.entities;

import java.time.LocalDate;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="garages")
@AttributeOverride(name="id", column = @Column(name="garage_id"))
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Garage extends BaseEntity{
	
	@Column(name="garage_name")
	private String garageName;
	
	
	private Address address;
	
	@Column(name= "garage_phone")
	private int garagePhone;
	
	@Column(name="total_mechanics")
	private int totalMechanics;
	
	@Column(name="opening_time")
	private LocalDate openingTime;
	
	@Column(name="closing_time")
	private LocalDate closingTime;
	
	@OneToMany
	@JoinColumn(name="timeslot_id")
	private TimeSlot timeslot;
	

}
