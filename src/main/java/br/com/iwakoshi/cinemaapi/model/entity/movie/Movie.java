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
import java.math.BigDecimal;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the movie database table.
 * 
 */
@Entity
@Table(name="movie")
@NamedQueries ({
  @NamedQuery(name="Movie.getMoviesComingSoonByPagination", query="SELECT m FROM Movie m WHERE m.releaseDate > :today"),
  @NamedQuery(name="Movie.countMoviesComingSoon", query="SELECT COUNT(m.id) FROM Movie m WHERE m.releaseDate > :today"),
})
public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="aspect_ratio", length=20)
	private String aspectRatio;

	@Column(precision=15, scale=2)
	private BigDecimal budget;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at", nullable=false)
	private Calendar createdAt;

	@Temporal(TemporalType.DATE)
	@Column(name="dvd_release")
	private Calendar dvdRelease;

	@Column(name="insert_user", nullable=false, length=50)
	private String insertUser;

	@Column(name="main_commentary", columnDefinition = "TEXT")
	private String mainCommentary;

	@Column(name="original_title", nullable=false, length=300)
	private String originalTitle;

	@Column(columnDefinition = "TEXT")
	private String overview;

	private byte[] poster;

	@Column(name="production_year", nullable=false, precision=4)
	private int productionYear;

	@Column(name="public_rating", precision=3, scale=1)
	private BigDecimal publicRating;

	@Column(precision=3, scale=1)
	private BigDecimal rating;

	@Temporal(TemporalType.DATE)
	@Column(name="release_date")
	private Calendar releaseDate;

	@Column(precision=5)
	private Integer runtime;

	@Column(name="update_user", length=50)
	private String updateUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Calendar updatedAt;

	//bi-directional many-to-one association to Certificate
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="certificate")
	private Certificate certificate;

	//bi-directional many-to-one association to Country
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="budget_currency")
	private Country country;

	public Movie() {
	  /* class body intentionally left blank */
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAspectRatio() {
		return this.aspectRatio;
	}

	public void setAspectRatio(String aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public BigDecimal getBudget() {
		return this.budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public Calendar getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public Calendar getDvdRelease() {
		return this.dvdRelease;
	}

	public void setDvdRelease(Calendar dvdRelease) {
		this.dvdRelease = dvdRelease;
	}

	public String getInsertUser() {
		return this.insertUser;
	}

	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}

	public String getMainCommentary() {
		return this.mainCommentary;
	}

	public void setMainCommentary(String mainCommentary) {
		this.mainCommentary = mainCommentary;
	}

	public String getOriginalTitle() {
		return this.originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getOverview() {
		return this.overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public byte[] getPoster() {
		return this.poster;
	}

	public void setPoster(byte[] poster) {
		this.poster = poster;
	}

	public int getProductionYear() {
		return this.productionYear;
	}

	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}

	public BigDecimal getPublicRating() {
		return this.publicRating;
	}

	public void setPublicRating(BigDecimal publicRating) {
		this.publicRating = publicRating;
	}

	public BigDecimal getRating() {
		return this.rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public Calendar getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Integer getRuntime() {
		return this.runtime;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Calendar getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Calendar updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Certificate getCertificate() {
		return this.certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}