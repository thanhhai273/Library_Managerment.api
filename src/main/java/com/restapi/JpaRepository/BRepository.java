package com.restapi.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.model.Book;

@Repository
public interface BRepository extends JpaRepository<Book, Long>{
	
}

