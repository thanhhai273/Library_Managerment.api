package com.restapi.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.model.Author;

@Repository
public interface ARepository extends JpaRepository<Author, Long>{
	
}