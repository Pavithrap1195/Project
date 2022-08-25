package com.books.stock.exchange.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.books.stock.exchange.Entities.Comics;

public interface ComicsRepository extends JpaRepository<Comics, Integer> {
	
	@Query("from Comics where comicHero=:hero ")
	public List<Comics> findComicsByHero(@Param("hero")String comicHero);

}
