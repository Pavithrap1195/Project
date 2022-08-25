package com.books.stock.exchange.Services;

import java.util.List;

import com.books.stock.exchange.Entities.Author;
import com.books.stock.exchange.exception.ResourceNotFoundException;

public interface AuthorService {

	public Author saveAuthor(Author author);

	public List<Author> getAllAuthors();

	public Author getAuthorByName(String authorName);

	public Author getAuthorById(int authorId);

	public Author updateAuthorById(Author author, int authorId);

	public void deleteAuthorById(int authorId);

	public Author assignPublicationToAuthor(int authorId, int publicationId);

}
