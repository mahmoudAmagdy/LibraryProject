package com.applc.library.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="borrowing_record")
@Data
public class BorrowingRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer bRecId;
	
	private Boolean complete;
	@ManyToOne
	private Book borrowedBook;
	
	@ManyToOne
	private Patron borrowingPatron;

}
