/*
 * Licensed to the Fabio Iwakoshi under one or more contributor license agreements. The ASF licenses
 * this file to You under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package br.com.iwakoshi.cinemaapi.model.entity.movie;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the certificate database table.
 * 
 */
@Entity
@Table(name="certificate")
@NamedQuery(name="Certificate.findAll", query="SELECT c FROM Certificate c")
public class Certificate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=50)
	private String description;

	//bi-directional many-to-one association to Country
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="country")
	private Country country;

	//bi-directional many-to-one association to Movie
	@OneToMany(mappedBy="certificate")
	private Set<Movie> movies;

	public Certificate() {
	  /* class body intentionally left blank */
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Set<Movie> getMovies() {
		return this.movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public Movie addMovie(Movie movie) {
		getMovies().add(movie);
		movie.setCertificate(this);

		return movie;
	}

	public Movie removeMovie(Movie movie) {
		getMovies().remove(movie);
		movie.setCertificate(null);

		return movie;
	}

}