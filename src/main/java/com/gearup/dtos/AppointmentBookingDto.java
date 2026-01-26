package com.gearup.dtos;

import java.time.LocalDate;

import com.gearup.entities.Status;
import com.gearup.entities.TransactionMode;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentBookingDto {

	@Future
	private LocalDate appointmentDate;

	private Status status;

	@NotNull(message = "Customer ID is required")
	private Long customerId;

	@NotNull(message = "Time Slot ID is required")
	private Long timeSlotId;

	private TransactionMode paymentMode = TransactionMode.CASH;

}
