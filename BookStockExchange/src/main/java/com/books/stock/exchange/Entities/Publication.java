package com.books.stock.exchange.Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * 
 * @author Pavithra Prakash
 *
 */
/**
 * 
 * Publication class is considered as an entity bean so that spring creates an
 * object and manages all other things
 * 
 * @Table tells to create table with the name as Publication
 * @Data represents using lombok library so that to generate
 *       getter,setters,etc..,
 */
@Entity
@Table(name = "Publication")
@Data
public class Publication {
	/**
	 * @Id represents publicationId will be primary key in the database
	 * @GeneratedValue represents that id should be generated automatically
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int publicationId;
	private String publisherName;
	private String publishingDate;

	
	  @JsonIgnore
	  @ManyToMany(mappedBy = "publications") 
	  private List<Author> authors;
	  
	  @OneToMany(mappedBy = "publication")
	  private List<Books> books;
	 

}
