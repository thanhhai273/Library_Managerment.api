package com.restapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.model.Book;
import com.restapi.JpaRepository.BookRepository;

@RestController
@RequestMapping("/api")

public class BookController {
	@Autowired
	BookRepository bookRepository;
	
	// liet ke tat ca cac sach
	@RequestMapping(value = "/books/", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> listAllBook() {
		List<Book> listAllBook = (List<Book>) bookRepository.findAll();
		if (listAllBook.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Book>>(listAllBook, HttpStatus.OK);
	}
	
	//Tim kiem sach theo ten
	@RequestMapping(value = "/books/{name}", method = RequestMethod.GET)
	public List<Book> findBook(@PathVariable String name) {
		List<Book> searchBook = (List<Book>) bookRepository.findByName(name);
		if (searchBook == null) {
			ResponseEntity.notFound().build();
		}
		return searchBook;
	}

	// Them sach moi
	@RequestMapping(value = "/books/", method = RequestMethod.POST)
	public Book addBook(@Valid @RequestBody Book book) {
		return bookRepository.save(book);
	}

	// cap nhat thong tin sach
	@RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Book> updateBook(@PathVariable("id") Long bookId, @Valid @RequestBody Book bookDetail) {
		Book book =   bookRepository.getOne(bookId);
		if (book == null) {
			return ResponseEntity.notFound().build();
		}
		
		book.setName(bookDetail.getName());
		book.setLanguage(bookDetail.getLanguage());
		book.setAuthor(bookDetail.getAuthor());
		book.setCategory(bookDetail.getCategory());
		book.setQuantity(bookDetail.getQuantity());
		book.setPublishing(bookDetail.getPublishing());

		Book updateBook = bookRepository.save(book);
		return ResponseEntity.ok(updateBook);

	}

	// Xoa sach
	@RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Book> deleteContact(@PathVariable(value = "id") Long id) {
		Book book = bookRepository.getOne(id);
	    if(book == null) {
	        return ResponseEntity.notFound().build();
	    }

	    bookRepository.delete(book);
	    return ResponseEntity.ok().build();
	}

}

