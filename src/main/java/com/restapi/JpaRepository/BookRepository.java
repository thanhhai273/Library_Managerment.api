package com.restapi.JpaRepository;

import java.util.List;

import javax.persistence.Table;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restapi.model.Book;

@Table(name="books")
@Repository
public interface BookRepository extends CrudRepository<Book, String>{
	@Query("select s from books s where s.name like %?1%")
	List<Book> findByName(String name);

}

