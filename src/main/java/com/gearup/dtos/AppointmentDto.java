package com.gearup.dtos;

import java.time.LocalDate;

import com.gearup.entities.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDto {
	

	private LocalDate appointmentDate;
	
	private Status status;

}
