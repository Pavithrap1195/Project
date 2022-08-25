package com.books.stock.exchange.Services;

import java.util.List;

import com.books.stock.exchange.Entities.Magazines;
import com.books.stock.exchange.exception.ResourceNotFoundException;

public interface MagazinesService {

	public Magazines saveMagazine(Magazines magazines);

	public List<Magazines> getAllMagazines();

	public Magazines updateMagazineById(Magazines magazine, int magazineId);

	public void deleteMagazineById(int magazineId) ;

	Magazines getMagazineByTitle(String authorTitle) ;

	Magazines getMagazineById(int magazineId) ;

}
