package com.books.stock.exchange.Repository;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.books.stock.exchange.Entities.Magazines;

@Repository
public interface MagazinesRepository extends JpaRepository<Magazines, Integer> {

	@Query("from Magazines where magazineTitle=:title")
	Magazines getMagazineByTitle(@Param("title")String magazineTitle);

}
