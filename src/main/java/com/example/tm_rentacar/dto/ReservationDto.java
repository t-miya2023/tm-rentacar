package com.example.tm_rentacar.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationDto {
	private Integer carId;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	private Integer amount;
}
