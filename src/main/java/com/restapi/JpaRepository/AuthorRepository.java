package com.restapi.JpaRepository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restapi.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
	@Query("select s from authors s where s.name like %?1%")
	List<Author> findByName(@Param("name") String name);
}
