package com.applc.library.entities;




import java.util.List;

import org.hibernate.validator.constraints.ISBN;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="book")
@Data
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer bookId;
	
	@NotNull
	@NotBlank(message = "what is the title?")
	private String bookTitle;
	
	@NotNull
	@NotBlank(message = "who is the author?")
	private String bookAuthor;
	
	@NotNull
	@Min(value = 1, message = "cannot enter a value smaller than 1")
	private Short bookPublishYr;
	
	@NotNull
	@ISBN(type = ISBN.Type.ANY)
	private String bookISBN;
	
	private Boolean borrowed;
	
	@OneToMany
	private List<BorrowingRecord> borrowedBookRec;
}
