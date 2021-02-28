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
import com.restapi.JpaRepository.ARepository;

@RestController
@RequestMapping("/api")

public class AuthorController {

	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	ARepository aRepository;

	// liet ke tat ca tac gia
	@RequestMapping(value = "/authors/", method = RequestMethod.GET)
	public ResponseEntity<List<Author>> listAllContact() {
		List<Author> listContact = (List<Author>) authorRepository.findAll();
		if (listContact.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Author>>(listContact, HttpStatus.OK);
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

	// them tac gia
	@RequestMapping(value = "/authors/", method = RequestMethod.POST)
	public Author addAuthor(@Valid @RequestBody Author author) {
		return authorRepository.save(author);
	}

	// cap nhat tac gia
	@RequestMapping(value = "/authors/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Author> updateAuthor(@PathVariable(value = "id") Long authorId,
			@Valid @RequestBody Author authorDetail) {
		Author author = aRepository.getOne(authorId);
		if (author == null) {
			return ResponseEntity.notFound().build();
		}
		
		author.setName(authorDetail.getName());
		author.setAge(authorDetail.getAge());
		author.setBirth(authorDetail.getBirth());
		author.setPhone(authorDetail.getPhone());
		author.setEmail(authorDetail.getEmail());

		Author updatedAuthor = aRepository.save(author);
		return ResponseEntity.ok(updatedAuthor);
	}

	// xoa tac gia
	@RequestMapping(value = "/authors/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Author> deleteContact(@PathVariable(value = "id") Long authorId) {
		Author author = aRepository.getOne(authorId);
		if (author == null) {
			return ResponseEntity.notFound().build();
		}

		aRepository.delete(author);
		return ResponseEntity.ok().build();
	}

}
