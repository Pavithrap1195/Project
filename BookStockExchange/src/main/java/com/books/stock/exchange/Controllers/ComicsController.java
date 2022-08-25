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

import com.books.stock.exchange.Entities.Comics;
import com.books.stock.exchange.Entities.ComicsRequest;
import com.books.stock.exchange.Services.ComicsService;

@RestController
public class ComicsController {
	
	@Autowired
	private ComicsService comicsService;

	@PostMapping("/saveComic")
	public Comics saveComic(@RequestBody Comics comics) {
		return comicsService.saveComic(comics);
	}

	@GetMapping("/getAllComics")
	public List<Comics> findAllComics() {
		return comicsService.getAllComics();
	}

	@PutMapping("/updateComic/{id}")
	public Comics updateComic(@RequestBody Comics comics, @PathVariable(name = "id") int comicId) {
		return comicsService.updateComicById(comics, comicId);
	}

	@DeleteMapping("/deleteComick/{id}")
	public String deleteComic(@PathVariable(name = "id") int comicId) {
		return comicsService.deleteComicById(comicId);
	}
	
	@PostMapping("/addComic")
	public Comics addComic(@RequestBody ComicsRequest request) {
		return comicsService.addComic(request);
	}
	
	@GetMapping("/getComicsByHero/{hero}")
	public List<Comics> findComicsByHero(@PathVariable("hero")String comicHero){
		return comicsService.findComicsByHero(comicHero);
	}
}
