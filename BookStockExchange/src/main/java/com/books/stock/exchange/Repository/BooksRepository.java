package com.books.stock.exchange.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.books.stock.exchange.Entities.Books;

public interface BooksRepository extends JpaRepository<Books, Integer> {
	
	@Query("from Books where author.authorName=:authorName and bookPublishedYear=:year")
	public List<Books> findAllBooksByYearAndAuthor(@Param("authorName")String authorName,@Param("year")String year);
	
	@Query("from Books where bookGenre=:genre and author.authorName=:authorName")
	public List<Books> findAllBooksByGenreAndAuthor(@Param("genre")String bookGenre,@Param("authorName")String authorName);

}
