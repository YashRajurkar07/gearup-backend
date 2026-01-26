package com.gearup.entities;

import java.time.LocalDateTime;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="timeslots")
@AttributeOverride(name="id", column=@Column(name="timeslot_ids"))
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class TimeSlot extends BaseEntity{
	
	@Column(name="start_time", nullable=false)
	private LocalDateTime startTime;
	
	@Column(name="end_time", nullable=false)
	private LocalDateTime EndTime;
	
	@Column(name="is_booked", nullable=false)
	private boolean isBooked;
	
	@ManyToOne
	@JoinColumn(name="garage_id", nullable=false)
	private Garage garage;
	
}
