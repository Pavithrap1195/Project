package com.books.stock.exchange.Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 
 * @author Pavithra Prakash
 *
 */
/**
 * 
 * Author class is considered as an entity bean so that spring creates an object
 * and manages all other things
 * 
 * @Table tells to create table with the name as Author
 * @Data represents using lombok library so that to generate
 *       getter,setters,etc..,
 */
@Entity
@Table(name = "Author")
@Data
public class Author {
	/**
	 * @Id represents publicationId will be primary key in the database
	 * @GeneratedValue represents that id should be generated automatically
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int authorId;

	@NotNull(message = "Author name should not be null")
	@NotEmpty(message = "Author name should not be empty")
	private String authorName;

	@ManyToMany
	@JoinTable(name = "authors_publications", joinColumns = @JoinColumn(name = "author_id"), inverseJoinColumns = @JoinColumn(name = "publication_id"))
	private List<Publication> publications;

	@OneToMany(mappedBy = "author")
	private List<Books> bookList;

	@OneToMany(mappedBy = "author")
	private List<Comics> comicsList;

}
