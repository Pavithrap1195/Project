package com.books.stock.exchange.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.stock.exchange.Entities.Author;
import com.books.stock.exchange.Entities.Comics;
import com.books.stock.exchange.Entities.ComicsRequest;
import com.books.stock.exchange.Repository.AuthorRepository;
import com.books.stock.exchange.Repository.ComicsRepository;

@Service
public class ComicsServiceImpl implements ComicsService {

	@Autowired
	private ComicsRepository comicsRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public Comics saveComic(Comics comics) {
		return comicsRepository.save(comics);
	}

	@Override
	public List<Comics> getAllComics() {
		return comicsRepository.findAll();
	}

	@Override
	public Comics updateComicById(Comics comics, int comicId) {
		Comics existingComic = comicsRepository.findById(comicId).orElse(null);
		existingComic.setComicTitle(comics.getComicTitle());
		existingComic.setComicPublishedYear(comics.getComicPublishedYear());
		existingComic.setComicHero(comics.getComicHero());
		return comicsRepository.save(existingComic);
	}

	@Override
	public String deleteComicById(int comicId) {
		comicsRepository.deleteById(comicId);
		return "Deleted Succesfully";
	}

	@Override
	public Comics addComic(ComicsRequest request) {
		Author author = authorRepository.findById(request.author_id).get();
		Comics comics = new Comics();
		comics.setComicTitle(request.comicTitle);
		comics.setComicPublishedYear(request.comicPublishedYear);
		comics.setComicHero(request.comicHero);
		comics.setAuthor(author);
		return comicsRepository.save(comics);
	}

	@Override
	public List<Comics> findComicsByHero(String comicHero) {
		return comicsRepository.findComicsByHero(comicHero);
	}

}
