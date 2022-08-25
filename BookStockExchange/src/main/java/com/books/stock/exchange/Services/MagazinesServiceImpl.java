package com.books.stock.exchange.Services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.books.stock.exchange.Entities.Magazines;
import com.books.stock.exchange.Repository.MagazinesRepository;
import com.books.stock.exchange.exception.ResourceAlreadyExistException;
import com.books.stock.exchange.exception.ResourceNotFoundException;

@Service
public class MagazinesServiceImpl implements MagazinesService {

	@Autowired
	private MagazinesRepository magazinesRepository;

	private static Logger logger = LogManager.getLogger(AuthorServiceImpl.class);

	@Override
	public Magazines saveMagazine(Magazines magazines) {
		logger.info("Saving Magazine details");
		Magazines savedMagazine = null;
		Magazines existingMagazine = null;
		existingMagazine = this.magazinesRepository.getMagazineByTitle(magazines.getMagazineTitle());
		if (existingMagazine != null) {
			logger.info("Fetching Magazine already exist, so not able to save the data again");
			throw new ResourceAlreadyExistException(
					"Fetching " + magazines.getMagazineTitle() + " Magazine is already exist");
		} else {
			savedMagazine = this.magazinesRepository.save(magazines);
			logger.debug("Magazine saved successfully");
			return savedMagazine;
		}
	}

	@Override
	public List<Magazines> getAllMagazines() {
		logger.info("Fetching All Magazines details");
		List<Magazines> listOfMagazines = this.magazinesRepository.findAll();
		if (listOfMagazines.isEmpty()) {
			logger.info("Magazines not found");
			throw new ResourceNotFoundException("Magazines not found");
		}
		logger.debug("All details fetched successfully");
		return listOfMagazines;
	}

	@Override
	public Magazines updateMagazineById(Magazines magazine, int magazineId)  {
		Magazines existingMagazine = magazinesRepository.findById(magazineId)
				.orElseThrow(() -> new ResourceNotFoundException("magazine not found with Id : " + magazineId));
		existingMagazine.setMagazineTitle(magazine.getMagazineTitle());
		existingMagazine.setMagazinePublishedYear(magazine.getMagazinePublishedYear());
		existingMagazine.setMagazineType(magazine.getMagazineType());
		return magazinesRepository.save(existingMagazine);
	}

	@Override
	public void deleteMagazineById(int magazineId)  {
		Magazines magazine = magazinesRepository.findById(magazineId)
				.orElseThrow(() -> new ResourceNotFoundException("magazine not found with Id : " + magazineId));
		magazinesRepository.delete(magazine);
	}

	@Override
	public Magazines getMagazineByTitle(String authorTitle) {
		Magazines magazine = magazinesRepository.getMagazineByTitle(authorTitle);
		if (magazine == null) {
			throw new ResourceNotFoundException("magazine not found with Title : " + authorTitle);
		}
		return magazine;
	}

	@Override
	public Magazines getMagazineById(int magazineId) {
		Magazines existingMagazine = magazinesRepository
				.findById(magazineId)
				.orElseThrow(() -> new ResourceNotFoundException("magazine not found with Id : " + magazineId));
		return existingMagazine;
	}

}
