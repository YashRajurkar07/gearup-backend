package com.gearup.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeSlotDto {

	private Long garageId;
	
	private LocalDateTime startTime;

	private LocalDateTime EndTime;

	@NotNull
	private boolean isBooked;
}
