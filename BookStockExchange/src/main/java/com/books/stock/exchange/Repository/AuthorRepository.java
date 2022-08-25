package com.books.stock.exchange.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.books.stock.exchange.Entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

	@Query("from Author where authorName=:name")
	public Author getAuthorByName(@Param("name") String authorName);

}
