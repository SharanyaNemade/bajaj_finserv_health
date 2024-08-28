package com.app.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegisterDto {
	//private String rollNumber;
	private String firstName;
	private String lastName;
	private String email;
	private String phonenumber;
}
