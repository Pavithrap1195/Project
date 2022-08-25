package com.books.stock.exchange.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.books.stock.exchange.Entities.Magazines;
import com.books.stock.exchange.Services.MagazinesService;
import com.books.stock.exchange.exception.ResourceNotFoundException;

@RestController
@EnableWebMvc
public class MagazinesController {

	private static Logger logger = LogManager.getLogger(MagazinesController.class);

	@Autowired
	private MagazinesService magazinesService;

	@PostMapping("/saveMagazine")
	public Magazines saveMagazine(@Valid @RequestBody Magazines magazine) {
		logger.info("Saving magazine details");
		Magazines savedMagazine = null;
		savedMagazine = magazinesService.saveMagazine(magazine);
		logger.debug("Magazine details Saved Succesfully");
		return savedMagazine;
	}

	@GetMapping("/findAllMagazines")
	public List<Magazines> findAllMagazines() {
		logger.info("Fetching All Author details");
		List<Magazines> listOfMagazines = null;
			logger.info("try block execution started");
			listOfMagazines = magazinesService.getAllMagazines();
			logger.debug("Magazines details Fetched Succesfully");
			logger.info("try block execution completed");
			return listOfMagazines;
	}

	@GetMapping("/findMagazineByTitle/{title}")
	public Magazines findMagazineByTitle(@Valid @PathVariable("title") String magazineTitle) {
			return magazinesService.getMagazineByTitle(magazineTitle);
	}

	@GetMapping("/findMagazineById/{id}")
	public Magazines findMagazineById(@Valid @PathVariable("id") int magazineId) {
			return magazinesService.getMagazineById(magazineId);
	}

	@PutMapping("/updateMagazine/{id}")
	public Magazines updateMagazine(@Valid @RequestBody Magazines magazine, @PathVariable(name = "id") int magazineId) {
			return magazinesService.updateMagazineById(magazine, magazineId);

	}

	@DeleteMapping("/deleteMagazine/{id}")
	public ResponseEntity<String> deleteMagazine(@Valid @PathVariable(name = "id") int magazineId){
		magazinesService.deleteMagazineById(magazineId);
		return new ResponseEntity<String>("Magazines not found with Id : " + magazineId, HttpStatus.NO_CONTENT);
	}

}
