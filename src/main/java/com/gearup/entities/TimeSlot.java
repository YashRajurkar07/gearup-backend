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
@Table(name="timeslots")
@AttributeOverride(name="id", column=@Column(name="timeslot_id"))
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class TimeSlot extends BaseEntity{
	
	@Column(name="start_time", nullable=false)
	private LocalDate startTime;
	
	@Column(name="end_time", nullable=false)
	private LocalDate EndTime;
	
	@Column(name="is_booked", nullable=false)
	private boolean isBooked;
	
}
