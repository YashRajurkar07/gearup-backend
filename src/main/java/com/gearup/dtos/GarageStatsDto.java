package com.gearup.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GarageStatsDto {

	private long appointmentsToday;
	
	private long pendingRequests;
	
	private double totalRevenue;
	
	private double averageRating;
}
