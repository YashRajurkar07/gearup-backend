package com.gearup.dtos;

import java.time.LocalDate;

import com.gearup.entities.Status;

import jakarta.validation.constraints.Future;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDto {
	
	@Future
	private LocalDate appointmentDate;
	
	private Status status;

}
