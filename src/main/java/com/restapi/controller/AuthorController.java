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

import com.restapi.model.Author;
import com.restapi.JpaRepository.AuthorRepository;
import com.restapi.JpaRepository.BookRepository;

@RestController
@RequestMapping("/api")

public class AuthorController {

	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	BookRepository bookRepository;

//	@PostMapping
//    public Author updateAuthor(@RequestBody Author author, @RequestBody BookRepository book) {
//        return Service.updateAuthor(author, book);
//    }

	// liet ke tat ca tac gia
	@RequestMapping(value = "/authors/", method = RequestMethod.GET)
	public ResponseEntity<List<Author>> listAllContact() {
		List<Author> listAuthor = (List<Author>) authorRepository.findAll();
		if (listAuthor.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Author>>(listAuthor, HttpStatus.OK);
	}

	// tim kiem tac gia
	@RequestMapping(value = "/authors/{name}", method = RequestMethod.GET)
	public List<Author> findBook(@PathVariable String name) {
		List<Author> searchAuthor = (List<Author>) authorRepository.findByName(name);
		if (searchAuthor == null) {
			ResponseEntity.notFound().build();
		}
		return searchAuthor;
	}

	// Tim sach theo authorId;
	@RequestMapping(value = "/authors/find/{id}", method = RequestMethod.GET)
	public Author findBook(@PathVariable Long id) {
		Author searchBook = authorRepository.getOne(id);
		if (searchBook == null) {
			ResponseEntity.notFound().build();
		}
		return searchBook;
	}

	// them tac gia
	@RequestMapping(value = "/authors/", method = RequestMethod.POST)
	public Author addAuthor(@Valid @RequestBody Author author) {
		return authorRepository.save(author);
	}

	// cap nhat tac gia
	@RequestMapping(value = "/authors/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Author> updateAuthor(@PathVariable(value = "id") Long authorId,
			@Valid @RequestBody Author authorDetail) {
		Author author = authorRepository.getOne(authorId);
		if (author == null) {
			return ResponseEntity.notFound().build();
		}

		author.setName(authorDetail.getName());
		author.setAge(authorDetail.getAge());
		author.setBirth(authorDetail.getBirth());
		author.setPhone(authorDetail.getPhone());
		author.setEmail(authorDetail.getEmail());

		Author updatedAuthor = authorRepository.save(author);
		return ResponseEntity.ok(updatedAuthor);
	}

	// xoa tac gia
	@RequestMapping(value = "/authors/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Author> deleteContact(@PathVariable(value = "id") Long authorId) {
		Author author = authorRepository.getOne(authorId);
		if (author == null) {
			return ResponseEntity.notFound().build();
		}

		authorRepository.delete(author);
		return ResponseEntity.ok().build();
	}

}

