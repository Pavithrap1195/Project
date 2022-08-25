package com.books.stock.exchange.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.stock.exchange.Entities.Author;
import com.books.stock.exchange.Entities.Books;
import com.books.stock.exchange.Entities.BooksRequest;
import com.books.stock.exchange.Entities.Publication;
import com.books.stock.exchange.Repository.AuthorRepository;
import com.books.stock.exchange.Repository.BooksRepository;
import com.books.stock.exchange.Repository.PublicationRepository;

@Service
public class BooksServiceImpl implements BooksService {

	@Autowired
	private BooksRepository booksRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private PublicationRepository publicationRepository;

	@Override
	public Books saveBook(Books books) {
		return booksRepository.save(books);
	}

	@Override
	public List<Books> getAllBooks() {
		return booksRepository.findAll();
	}

	@Override
	public Books updateBookById(Books books, int bookId) {
		Books existingBook = booksRepository.findById(bookId).orElse(null);
		existingBook.setBookTitle(books.getBookTitle());
		existingBook.setBookPublishedYear(books.getBookPublishedYear());
		existingBook.setBookGenre(books.getBookGenre());
		return booksRepository.save(existingBook);
	}

	@Override
	public String deleteBookById(int bookId) {
		booksRepository.deleteById(bookId);
		return "Deleted Succesfully";
	}

	@Override
	public Books addBook(BooksRequest request) {
		Author author = authorRepository.findById(request.author_id).get();
		Publication publications = publicationRepository.findById(request.publication_id).get();
		Books book = new Books();
		book.setBookTitle(request.bookTitle);
		book.setBookPublishedYear(request.bookPublishedYear);
		book.setBookGenre(request.bookGenre);
		book.setAuthor(author);
		book.setPublication(publications);
		return booksRepository.save(book);
	}

	@Override
	public List<Books> findAllBooksByYearAndAuthor(String authorName, String year) {
		return booksRepository.findAllBooksByYearAndAuthor(authorName, year);
	}

	@Override
	public List<Books> findAllBooksByGenreAndAuthor(String bookGenre, String authorName) {
		return booksRepository.findAllBooksByGenreAndAuthor(bookGenre, authorName);
	}


}
