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
 * The persistent class for the country database table.
 * 
 */
@Entity
@Table(name="country")
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=2)
	private String code;

	@Column(name="currency_iso_code", length=3)
	private String currencyIsoCode;

	@Column(name="currency_name", length=50)
	private String currencyName;

	@Column(name="currency_symbol", length=5)
	private String currencySymbol;

	@Column(nullable=false, length=80)
	private String name;

	//bi-directional many-to-one association to Certificate
	@OneToMany(mappedBy="country")
	private Set<Certificate> certificates;

	//bi-directional many-to-one association to Movie
	@OneToMany(mappedBy="country")
	private Set<Movie> movies;

	public Country() {
	  /* class body intentionally left blank */
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCurrencyIsoCode() {
		return this.currencyIsoCode;
	}

	public void setCurrencyIsoCode(String currencyIsoCode) {
		this.currencyIsoCode = currencyIsoCode;
	}

	public String getCurrencyName() {
		return this.currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencySymbol() {
		return this.currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Certificate> getCertificates() {
		return this.certificates;
	}

	public void setCertificates(Set<Certificate> certificates) {
		this.certificates = certificates;
	}

	public Certificate addCertificate(Certificate certificate) {
		getCertificates().add(certificate);
		certificate.setCountry(this);

		return certificate;
	}

	public Certificate removeCertificate(Certificate certificate) {
		getCertificates().remove(certificate);
		certificate.setCountry(null);

		return certificate;
	}

	public Set<Movie> getMovies() {
		return this.movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public Movie addMovie(Movie movie) {
		getMovies().add(movie);
		movie.setCountry(this);

		return movie;
	}

	public Movie removeMovie(Movie movie) {
		getMovies().remove(movie);
		movie.setCountry(null);

		return movie;
	}

}