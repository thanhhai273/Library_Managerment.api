package com.restapi.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "books")
@Table(name = "books")
@JsonIgnoreProperties({ "HibernateLazyInitializer", "Handler" })

public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String language;
	private String category;
	private String author;
	private int quantity;
	private Date publishing;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	public Book() {
		id = 0;
	}	

	@ManyToMany(mappedBy = "books", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnoreProperties("books")
	//@JsonBackReference
	
	private Set<Author> authors;
	

	public Book(int id, String name, String language, String category, String author, int quantity, Date publishing) {
		this.id = id;
		this.name = name;
		this.language = language;
		this.category = category;
		this.author = author;
		this.quantity = quantity;
		this.publishing = publishing;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getPublishing() {
		return publishing;
	}

	public void setPublishing(Date publishing) {
		this.publishing = publishing;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Set<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
	

}

