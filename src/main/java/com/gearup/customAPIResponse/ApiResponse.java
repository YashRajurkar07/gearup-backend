package com.gearup.customAPIResponse;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
	
	private String message;
	private String status;
	private LocalDate timeStamp;
	
	public ApiResponse(String message, String status) {
		this.message = message;
		this.status = status;
		this.timeStamp = LocalDate.now();
	}
	
}
