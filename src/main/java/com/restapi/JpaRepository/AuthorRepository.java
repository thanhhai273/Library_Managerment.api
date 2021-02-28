package com.restapi.JpaRepository;

import java.util.List;

import javax.persistence.Table;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restapi.model.Author;

@Table(name="authors")
@Repository
public interface AuthorRepository extends CrudRepository<Author, String>{
	@Query("select s from authors s where s.name like %?1%")
	List<Author> findByName(String name);
}
