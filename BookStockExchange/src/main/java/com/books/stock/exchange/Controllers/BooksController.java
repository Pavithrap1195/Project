package com.books.stock.exchange.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.books.stock.exchange.Entities.Books;
import com.books.stock.exchange.Entities.BooksRequest;
import com.books.stock.exchange.Services.BooksService;

@RestController
public class BooksController {

	@Autowired
	private BooksService booksService;

	@PostMapping("/saveBooks")
	public Books saveBooks(@RequestBody Books books) {
		return booksService.saveBook(books);
	}

	@GetMapping("/getAllBooks")
	public List<Books> findAllBooks() {
		return booksService.getAllBooks();
	}

	@PutMapping("/updateBook/{id}")
	public Books updateBook(@RequestBody Books books, @PathVariable(name = "id") int bookId) {
		return booksService.updateBookById(books, bookId);
	}

	@DeleteMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable(name = "id") int bookId) {
		return booksService.deleteBookById(bookId);
	}

	// For Mapping between books,author and publications
	@PostMapping("/addBook")
	public Books addBook(@RequestBody BooksRequest request) {
		return booksService.addBook(request);
	}

	@GetMapping("/getBooksByYearAndAuthor/{author}/{year}")
	public List<Books> getBooksByYearAndAuthor(@PathVariable("author") String authorName,@PathVariable("year") String bookPublishedYear
			) {
		return booksService.findAllBooksByYearAndAuthor(authorName, bookPublishedYear);
	}
	
	@GetMapping("/findAllBooksByGenreAndAuthor/{genre}/{author}")
	public List<Books> findAllBooksByGenreAndAuthor(@PathVariable("genre")String bookGenre,@PathVariable("author")String authorName){
		return booksService.findAllBooksByGenreAndAuthor(bookGenre, authorName);
	}
}
