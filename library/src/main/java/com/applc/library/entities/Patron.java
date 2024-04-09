package com.applc.library.entities;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="patron")
@Data
public class Patron {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer patronId;
	

	@NotNull
	@NotBlank(message = "what is your email?")
	@Email
	private String patronEmail;
	
	@NotNull
	@NotBlank(message = "the first name is empty!")
	private String patronFirstName;
	
	@NotNull
	@NotBlank(message = "the last name is empty!")
	private String patronLastName;
	
	@NotNull
	@NotBlank(message = "the phone number is empty!")
	private String patronPhoneNumber;
	
	@OneToMany
	private List<BorrowingRecord> patronBorrowList;
	

}
