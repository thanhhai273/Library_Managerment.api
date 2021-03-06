package com.restapi.JpaRepository;

import java.util.List;

import javax.persistence.Table;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restapi.model.Book;

@Table(name = "books")
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	@Query("select s from books s where s.name like %?1%")
	Book findByName(@Param("name") String name);

	@Query("select a.id,count(b.name) as numberOfBook from books b, authors a where a.id =?1 and a.name=b.author group by b.author ")
	List<NumberOfBook> findAllBy(Long id);

}



