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

import com.books.stock.exchange.Entities.Publication;
import com.books.stock.exchange.Services.PublicationService;

@RestController
public class PublicationsController {

	@Autowired
	private PublicationService publicationService;

	@PostMapping("/savePublication")
	public Publication savePublication(@RequestBody Publication publication) {
		return publicationService.savePublication(publication);
	}

	@GetMapping("/getAllPublications")
	public List<Publication> findAllPublications() {
		return publicationService.getAllPublications();
	}

	@PutMapping("/updatePublication/{id}")
	public Publication updatePublication(@RequestBody Publication publication,
			@PathVariable(name = "id") int publicationId) {
		return publicationService.updatePublicationById(publication, publicationId);
	}

	@DeleteMapping("/deletePublication/{id}")
	public String deletePublication(@PathVariable(name = "id") int publicationId) {
		return publicationService.deletePublicationById(publicationId);
	}
}
