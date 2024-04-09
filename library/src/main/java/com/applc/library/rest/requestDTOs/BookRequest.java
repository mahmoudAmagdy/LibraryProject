package com.applc.library.rest.requestDTOs;


import lombok.Data;

@Data
public class BookRequest {
	private Integer bookId;
	private String bookTitle;
	private String bookAuthor;
	private Short bookPublishYr;
	private String bookISBN;

}
