package com.books.stock.exchange.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.stock.exchange.Entities.Publication;
import com.books.stock.exchange.Repository.PublicationRepository;

@Service
public class PublicationServiceImpl implements PublicationService {

	@Autowired
	private PublicationRepository publicationRepository; 
	
	@Override
	public Publication savePublication(Publication publication) {
		
		return publicationRepository.save(publication) ;
	}

	@Override
	public List<Publication> getAllPublications() {
		return publicationRepository.findAll();
	}

	@Override
	public Publication updatePublicationById(Publication publication, int publicationId) {
		Publication existingPublication=publicationRepository.findById(publicationId).orElse(null);
		existingPublication.setPublisherName(publication.getPublisherName());
		existingPublication.setPublishingDate(publication.getPublishingDate());
		return publicationRepository.save(existingPublication);
	}

	@Override
	public String deletePublicationById(int publicationId) {
		publicationRepository.deleteById(publicationId);
		return "Deleted Succesfully";
	}

	

	
}
