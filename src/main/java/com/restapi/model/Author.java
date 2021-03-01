package com.restapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "authors")
@Table(name="authors")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Author implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Date birth;
	private int age;
	private Long phone;
	private String email;
	private int numberOfBook;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	public Author(){
		id=0;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "author_book",
			joinColumns = @JoinColumn(name= "authorId", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="bookId", referencedColumnName = "id"))
	 @JsonIgnoreProperties("authors")
	//@JsonManagedReference
	
	private Set<Book> books ;
	
	public Author(int id, String name, Date birth, int age, Long phone, String email, int numberOfBook ) {
		this.id=id;
		this.name=name;
		this.birth=birth;
		this.age=age;
		this.phone=phone;
		this.email=email;
		this.numberOfBook=numberOfBook;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public int getNumberOfBook() {
		return numberOfBook;
	}

	public void setNumberOfBook(int numberOfBook) {
		this.numberOfBook = numberOfBook;
	}

	
		
}

