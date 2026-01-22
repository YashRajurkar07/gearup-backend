package com.gearup.entities;

import java.time.LocalDate;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="appointments")
@AttributeOverride(name="id", column=@Column(name="appointment_id"))
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Appointment extends BaseEntity{
	
	private LocalDate appointmentDate;
	private Status status;
	private Customer customer;
	private Timeslot timeslot;
	

}
