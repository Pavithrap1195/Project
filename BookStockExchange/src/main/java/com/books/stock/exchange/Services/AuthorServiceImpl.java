package com.books.stock.exchange.Services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.books.stock.exchange.Entities.Author;
import com.books.stock.exchange.Entities.Publication;
import com.books.stock.exchange.Repository.AuthorRepository;
import com.books.stock.exchange.Repository.PublicationRepository;
import com.books.stock.exchange.advice.ApplicationExceptionHandler;
import com.books.stock.exchange.exception.ResourceNotFoundException;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private PublicationRepository publicationRepository;

	private static Logger logger = LogManager.getLogger(AuthorServiceImpl.class);

	@Override
	public Author saveAuthor(Author author) {
		logger.info("Saving Author details");
		Author savedAuthor = null;

		try {
			savedAuthor = this.authorRepository.save(author);
			logger.debug("Author saved successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savedAuthor;
	}

	@Override
	public List<Author> getAllAuthors() {
		logger.info("Fetching All Author details");
		List<Author> listOfAuthor = this.authorRepository.findAll();

		if (listOfAuthor == null) {
			logger.error(new ResourceNotFoundException("Author details not found"));
			throw new ResourceNotFoundException("Author details not found");
		}

		logger.debug("All details fetched successfully");
		return listOfAuthor;
	}

	@Override
	public Author updateAuthorById(Author author, int authorId) {
		logger.info("Updating Author details");
		Author existingAuthor = authorRepository.findById(authorId)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found with Id : " + authorId));
		existingAuthor.setAuthorName(author.getAuthorName());

		logger.debug("Details updated successfully");
		return authorRepository.save(existingAuthor);
	}

	@Override
	public void deleteAuthorById(int authorId) {
		logger.info("Deleting Author details");
		Author author = authorRepository.findById(authorId)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found with Id : " + authorId));

		logger.debug("Details deleted successfully");
		authorRepository.delete(author);
	}

	@Override
	public Author assignPublicationToAuthor(int authorId, int publicationId) {
		logger.info("Assigning Author to the publication");

		List<Publication> publicationSet = null;
		Author author = authorRepository.findById(authorId)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found with Id : " + authorId));
		logger.info("Author details fetched successfully by given Id");

		Publication publication = publicationRepository.findById(publicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found with Id : " + authorId));
		logger.info("Publication details fetched successfully by given Id");

		logger.info(
				"Fetching and assigning publications reference variable present in Author class which is empty into the List of Publication empty reference variable");
		publicationSet = author.getPublications();

		logger.info("Adding fetched publication details into the publicationSet reference variable which is empty");
		publicationSet.add(publication);

		logger.info("Set the property of publicationSet into the author entity");
		author.setPublications(publicationSet);

		logger.debug("Author assigned successfully to the respective publication");
		return authorRepository.save(author);
	}

	@Override
	public Author getAuthorByName(String authorName) {
		logger.info("Fetching Author details by author name");
		Author author = authorRepository.getAuthorByName(authorName);

		if (author == null) {
			logger.error(new ResourceNotFoundException("Author not found with name : " + authorName));
			throw new ResourceNotFoundException("Author not found with name : " + authorName);
		}

		logger.debug("Author details fetched successfully");
		return author;
	}

	@Override
	public Author getAuthorById(int authorId) throws ResourceNotFoundException {
		logger.info("Fetching Author details by author id");
		Author existingAuthor = authorRepository.findById(authorId).orElse(null);

		if (existingAuthor == null) {
			logger.error(new ResourceNotFoundException("Author not found with name : " + authorId));
			throw new ResourceNotFoundException("Author not found with Id : " + authorId);
		}

		logger.debug("Author details fetched successfully");
		return existingAuthor;
	}

}
