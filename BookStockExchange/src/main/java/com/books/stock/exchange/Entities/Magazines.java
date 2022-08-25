package com.books.stock.exchange.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

/**
 * 
 * @author Pavithra Prakash
 *
 */
/**
 * 
 * Magazines class is considered as an entity bean so that spring creates an
 * object and manages all other things
 * 
 * @Table tells to create table with the name as Magazines
 * @Data represents using lombok library so that to generate
 *       getter,setters,etc..,
 */
@Entity
@Table(name = "Magazines")
@Data
public class Magazines {
	/**
	 * @Id represents publicationId will be primary key in the database
	 * @GeneratedValue represents that id should be generated automatically
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int magazineId;
	
	@NotNull(message = "Magazine title should not be null")
	@NotEmpty(message = "Magazine title should not be empty")
	private String magazineTitle;
	
	@NotNull(message = "Magazine year should not be null")
	@Digits(integer = 4, fraction = 0)
	private String magazinePublishedYear;
	
	@NotNull(message = "Magazine Type should not be null")
	private String magazineType;

}
