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

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import br.com.iwakoshi.cinemaapi.BuilderMovie;
import br.com.iwakoshi.cinemaapi.MockitoExtension;
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
    Mockito.when(movieService.getMoviesComingSoonByPagination(0, 30))
        .thenReturn(BuilderMovie.createMovies());

    // Act
    List<Movie> listMovies = movieService.getMoviesComingSoonByPagination(0, 30);

    // Then
    Assertions.assertEquals(2, listMovies.size());
    Movie movie = listMovies.get(0);
    Assertions.assertEquals(1999, movie.getProductionYear());
    Assertions.assertEquals(1L, movie.getId().longValue());
    Assertions.assertEquals(120, movie.getRuntime().intValue());
    Assertions.assertEquals("4:3", movie.getAspectRatio());
    Assertions.assertEquals(new BigDecimal(100), movie.getBudget());
    Assertions.assertEquals(1, movie.getCertificate().getId().intValue());
    Assertions.assertEquals("BR", movie.getCertificate().getCountry().getCode());
    Assertions.assertEquals("BRL", movie.getCertificate().getCountry().getCurrencyIsoCode());
    Assertions.assertEquals("Real", movie.getCertificate().getCountry().getCurrencyName());
    Assertions.assertEquals("R$", movie.getCertificate().getCountry().getCurrencySymbol());
    Assertions.assertEquals("Brazil", movie.getCertificate().getCountry().getName());
    Assertions.assertEquals("12 Anos", movie.getCertificate().getDescription());
    Assertions.assertEquals(null, movie.getCountry());
    Assertions.assertTrue(movie.getCreatedAt().before(Calendar.getInstance()));
    Assertions.assertTrue(movie.getDvdRelease().before(Calendar.getInstance()));
    Assertions.assertEquals("cinema@cinema-api.com.br", movie.getInsertUser());
    Assertions.assertEquals("any thing", movie.getMainCommentary());
    Assertions.assertEquals("The Matrix", movie.getOriginalTitle());
    Assertions.assertEquals("The Matrix Overview", movie.getOverview());
    Assertions.assertEquals(null, movie.getPoster());
    Assertions.assertEquals(new BigDecimal(9.9), movie.getPublicRating());
    Assertions.assertEquals(new BigDecimal(9.8), movie.getRating());
    Assertions.assertTrue(movie.getReleaseDate().before(Calendar.getInstance()));
    Assertions.assertTrue(movie.getUpdatedAt().before(Calendar.getInstance()));
    Assertions.assertEquals("cinema@cinema-api.com.br", movie.getUpdateUser());
  }

  /**
   * Test method for
   * {@link br.com.iwakoshi.cinemaapi.service.movie.MovieService#countMoviesComingSoon()}.
   */
  @Test
  void testCountMoviesComingSoon() {
    // Arrange
    Mockito.when(movieService.countMoviesComingSoon())
        .thenReturn((long) BuilderMovie.createMovies().size());

    // Act
    Long count = movieService.countMoviesComingSoon();

    // Then
    Assertions.assertEquals(2, count.longValue());
  }

}
