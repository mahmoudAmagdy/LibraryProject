package com.applc.library.rest.responseDTOs;

import lombok.Data;

@Data
public class PatronResponse {
	private String patronId;
	private String patronEmail;
	private String patronFirstName;
	private String patronLastName;
	private String patronPhoneNumber;

}
