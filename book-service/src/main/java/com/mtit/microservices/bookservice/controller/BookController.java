package com.mtit.microservices.bookservice.controller;

import com.mtit.microservices.bookservice.dto.BookRequest;
import com.mtit.microservices.bookservice.dto.BookResponse;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/book")
public class BookController {
	
	List<BookRequest> allBooks = new ArrayList<BookRequest>();

	@GetMapping("/")
    public String index(){
        return "Hello Welcome to Book Service !";
    }
	
    @PostMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody BookResponse addBook(@RequestBody BookRequest bookRequest) {
        var bookResponse = new BookResponse();
        String newbookid = UUID.randomUUID().toString();
        var bookReq = bookRequest;
        bookReq.setBookid(newbookid);
        bookResponse.setBookId(newbookid);
        bookResponse.setMessage("New Book Added successfully");
        allBooks.add(bookReq);
        return bookResponse;
    }
    
    @PutMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody BookResponse updateBook(@RequestParam String bookid, 
    		@RequestBody BookRequest bookRequest) {
        boolean isExists = false;
    	for (BookRequest bookReq : allBooks) {
			if(bookReq.getBookid().equals(bookid)) {
				isExists = true;
				allBooks.remove(bookReq);
				break;
			}
		}
    	var bookResponse = new BookResponse();
    	bookResponse.setBookId(bookid);
    	if(isExists) {
    		bookRequest.setBookid(bookid);
    		allBooks.add(bookRequest);
    		bookResponse.setMessage("Book Updated successfully");
    	}else {
    		bookResponse.setMessage("Book Updated failed");
    	}
    	return bookResponse;
    }
    
    @GetMapping("/all")
    public @ResponseBody List<BookRequest>  allBooks(){
        return allBooks;
    }
    
    @GetMapping("/search")
    public @ResponseBody BookRequest  searchBook(@RequestParam String bookid){
    	for (BookRequest bookReq : allBooks) {
			if(bookReq.getBookid().equals(bookid)) {
				return bookReq;
			}
		}
    	return null;
    }
    
    @DeleteMapping(produces = "application/json")
    public @ResponseBody BookResponse removeBook(@RequestParam String bookid) {
        boolean isExists = false;
    	for (BookRequest bookReq : allBooks) {
			if(bookReq.getBookid().equals(bookid)) {
				isExists = true;
				allBooks.remove(bookReq);
				break;
			}
		}
    	if(isExists) {
    		var bookResponse = new BookResponse();
            bookResponse.setBookId(bookid);
    		bookResponse.setMessage("Book Removed successfully");
    		return bookResponse;
    	}else {
    		return null;
    	}
    }
    
}
