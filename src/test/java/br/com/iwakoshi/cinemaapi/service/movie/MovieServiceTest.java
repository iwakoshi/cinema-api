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
package br.com.iwakoshi.cinemaapi.service.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.iwakoshi.cinemaapi.BuilderMovie;
import br.com.iwakoshi.cinemaapi.model.entity.movie.Movie;
import br.com.iwakoshi.cinemaapi.model.repository.movie.MovieRepository;

/**
 * Test MovieService
 * 
 * @author Fabio Iwakoshi
 *
 */
@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

	@Mock
	private MovieRepository movieRepository;

	@InjectMocks
	private MovieService movieService;

	/**
	 * Test method for
	 * {@link br.com.iwakoshi.cinemaapi.service.movie.MovieService#getMoviesComingSoonByPagination(int, int)}.
	 */
	@Test
	void testGetMoviesComingSoonByPagination() {
		// Arrange
		when(movieService.getMoviesComingSoonByPagination(0, 30)).thenReturn(BuilderMovie.createMovies());

		// Act
		List<Movie> listMovies = movieService.getMoviesComingSoonByPagination(0, 30);

		// Then
		Movie movie = listMovies.get(0);
		assertEquals(2, listMovies.size());
		assertEquals(1999, movie.getProductionYear());
		assertEquals(1L, movie.getId().longValue());
		assertEquals(120, movie.getRuntime().intValue());
		assertEquals("4:3", movie.getAspectRatio());
		assertEquals(new BigDecimal(100), movie.getBudget());
		assertEquals(1, movie.getCertificate().getId().intValue());
		assertEquals("BR", movie.getCertificate().getCountry().getCode());
		assertEquals("BRL", movie.getCertificate().getCountry().getCurrencyIsoCode());
		assertEquals("Real", movie.getCertificate().getCountry().getCurrencyName());
		assertEquals("R$", movie.getCertificate().getCountry().getCurrencySymbol());
		assertEquals("Brazil", movie.getCertificate().getCountry().getName());
		assertEquals("12 Anos", movie.getCertificate().getDescription());
		assertEquals(null, movie.getCountry());
		assertTrue(movie.getCreatedAt().before(Calendar.getInstance()));
		assertTrue(movie.getDvdRelease().before(Calendar.getInstance()));
		assertEquals("cinema@cinema-api.com.br", movie.getInsertUser());
		assertEquals("any thing", movie.getMainCommentary());
		assertEquals("The Matrix", movie.getOriginalTitle());
		assertEquals("The Matrix Overview", movie.getOverview());
		assertEquals(null, movie.getPoster());
		assertEquals(new BigDecimal(9.9), movie.getPublicRating());
		assertEquals(new BigDecimal(9.8), movie.getRating());
		assertTrue(movie.getReleaseDate().before(Calendar.getInstance()));
		assertTrue(movie.getUpdatedAt().before(Calendar.getInstance()));
		assertEquals("cinema@cinema-api.com.br", movie.getUpdateUser());
	}

	/**
	 * Test method for
	 * {@link br.com.iwakoshi.cinemaapi.service.movie.MovieService#countMoviesComingSoon()}.
	 */
	@Test
	void testCountMoviesComingSoon() {
		// Arrange
		when(movieService.countMoviesComingSoon()).thenReturn((long) BuilderMovie.createMovies().size());

		// Act
		Long count = movieService.countMoviesComingSoon();

		// Then
		assertEquals(2, count.longValue());
	}

}
