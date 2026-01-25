package com.gearup.entities;

import java.time.LocalTime;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="garages")
@AttributeOverride(name="id", column = @Column(name="garage_ids"))
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Garage extends BaseEntity{
	
	@Column(name="garage_name")
	private String garageName;
	
	@Embedded
	private Address address;
	
	@Column(name= "garage_phone")
	private String garagePhone;
	
	@Column(name="total_mechanics")
	private int totalMechanics;
	
	@Column(name="opening_time")
	private LocalTime openingTime;
	
	@Column(name="closing_time")
	private LocalTime closingTime;
	
	@Column(name="is_active")
	private boolean isActive;
	
	@ManyToOne
	@JoinColumn(name="owner_id", nullable = false)
	private Owner owner;
	
//	@OneToMany
//	@JoinColumn(name="timeslot_id")
//	private List<TimeSlot> timeslot;
//	

}
