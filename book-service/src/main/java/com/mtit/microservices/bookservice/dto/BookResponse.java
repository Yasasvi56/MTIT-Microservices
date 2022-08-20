package com.mtit.microservices.bookservice.dto;

public class BookResponse {

    private String bookId;
    private String message;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
