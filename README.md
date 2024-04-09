Hello, this is how you can run this application.

First, you will need to head to the application.properties file and put in the information for your datasource url. You will also need to put in the username and password if you are using Postgres or MySQL.
You will need to replace the datasource driver if you are not using Postgres.

After this, you can run the application through the package com.applc.library -> LibraryApplication.java
Run this file as a Spring Boot Application if on STS/Eclipse.

If you are going to be testing the APIs, the paths are as follow:

The first path in the BookController.java file is POST:api/books
when paired with a body which contains something like the following:
{
    "bookTitle":"Book 1",
    "bookAuthor":"Author 1",
    "bookPublishYear":1900,
    "bookISBN":"0-4480-6921-0"
}
the data of your created book will be returned to you, along with a boolean "borrowed" to indicate whether this book is borrowed or not.
Please note that the ISBN has to be accurate to the format and unique to each entry, so here are a list of ISBNs that you can use:
978-4-3878-5030-4
0-3067-2571-1
0-5026-6702-8
0-7122-3807-7
978-2-1297-7579-3

The second path is GET api/books
this path returns all the books available in the library.

The third path is GET api/books/{bookId}
this path returns the book with the bookId used in the path.
Be sure to use this path after having inserted a book, or else you will simply be given a "BookDoesNotExist" exception.

The fourth path is PUT api/books/{bookId}
similar to the previous path, this path updates a book with the bookId in the path with whatever you put in the body.
You can try this body to check for changes:
{
    "bookTitle":"Not Book 1",
    "bookAuthor":"Not Author 1",
    "bookPublishYear":1901,
    "bookISBN":"0-4270-3695-X"
}

The fifth path is DELETE api/books/{bookId}
as with the previous paths, this one deletes the book with the bookId in the path


Moving on to patron paths in PatronController.java

The first path is POST api/patrons
an example for this path that can be used is:
{
    "patronEmail":"a@g.l",
    "patronFirstName":"Mido",
    "patronLastName":"Tamam",
    "patronPhoneNumber":"0111234"
}
the email follows an actual email format and it is the unique identifier to each entry in the
repository/table. 

The second path is GET api/patrons
this path returns all available patrons in the database.

The third path is GET api/patrons/{patronId}
this path returns a patron with the patronId in the path.

The fourth path is PUT api/patrons/{patronId}
this path updates a patron with id patronId in the header.

The fifth path is DELETE api/patrons/{patronId}
this path deletes a patron with id patronId in the header.


The final two paths are for borrowing, and they are in the BorrowingController.java file.

The first path is POST /api/borrow/{bookId}/patron/{patronId}
this one creates a BorrowingRecord entity in the borrowing_record table with the record containing
the patron with patronId, the book with bookId, and a boolean to check whether the borrowing record is
underway or complete.

The second path is PUT /api/borrow/{bookId}/patron/{patronId}
this path updates an already existing BorrowingRecord entity in the database so that the boolean
(complete) is set to true, signalling that the patron with patronId has returned the
book with bookId to the library.


With regards to tests, I am afraid I was not able to finish them in time because I could not start the project until yesterday evening, 
so I was unable to complete them. I apologize for my lack of professionalism.
