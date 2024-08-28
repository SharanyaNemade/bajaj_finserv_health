package com.app.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReservationReqDTO
{

	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private Long roomId;
	private Long guestId;
	
}
