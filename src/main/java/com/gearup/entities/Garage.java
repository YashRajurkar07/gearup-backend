package com.gearup.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
	private LocalDate openingTime;
	
	@Column(name="closing_time")
	private LocalDate closingTime;
	
	@OneToMany
	@JoinColumn(name="timeslot_id")
	private List<TimeSlot> timeslot;
	

}
