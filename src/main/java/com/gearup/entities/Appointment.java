package com.gearup.entities;

import java.time.LocalDate;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	
	@Column(name="appointment_date")
	private LocalDate appointmentDate;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="customer_id", nullable = false)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="garage_id", nullable = false)
	private Garage garage;
	
	@OneToOne
	@JoinColumn(name="time_slot", nullable = false)
	private TimeSlot timeslot;
	
	@OneToOne
	@JoinColumn(name="rating_id")
	private Rating rating;
	
	@OneToOne
	@JoinColumn(name="payment_id", nullable= false)
	private Payment payment;

}
