package com.applc.library.rest.requestDTOs;

import lombok.Data;

@Data
public class PatronRequest {
	private Integer patronId;
	private String patronEmail;
	private String patronFirstName;
	private String patronLastName;
	private String patronPhoneNumber;

}
