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
package br.com.iwakoshi.cinemaapi.resource.movie;

import java.util.List;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import br.com.iwakoshi.cinemaapi.BuilderMovie;
import br.com.iwakoshi.cinemaapi.MockitoExtension;
import br.com.iwakoshi.cinemaapi.model.entity.movie.Movie;
import br.com.iwakoshi.cinemaapi.service.movie.MovieService;

/**
 * @author Fabio Iwakoshi
 *
 */
@ExtendWith(MockitoExtension.class)
public class MovieResourceTest {

  @Mock
  private MovieService movieService;

  @InjectMocks
  private MovieResource movieResource;

  private List<Movie> movies = BuilderMovie.createMovies();

  @Test
  void testGetMoviesComingSoonByPaginationOnePage() {
    // Arrange
    Mockito.when(movieService.getMoviesComingSoonByPagination(0, 30)).thenReturn(movies);
    Mockito.when(movieService.countMoviesComingSoon()).thenReturn((long) movies.size());

    // Act
    Response response = movieResource.getMoviesComingSoonByPagination(0, 30);

    // Then
    Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    Assertions.assertEquals("rows 0-2/2", response.getHeaderString("Content-Range"));
    Assertions.assertNotNull(response.getEntity());
  }

  @Test
  void testGetMoviesComingSoonByPaginationFisrtPage() {
    // Arrange
    Mockito.when(movieService.getMoviesComingSoonByPagination(0, 1))
        .thenReturn(movies.subList(0, 1));
    Mockito.when(movieService.countMoviesComingSoon()).thenReturn((long) movies.size());

    // Act
    Response response = movieResource.getMoviesComingSoonByPagination(0, 1);

    // Then
    Assertions.assertEquals(Response.Status.PARTIAL_CONTENT.getStatusCode(), response.getStatus());
    Assertions.assertEquals("rows 0-1/2", response.getHeaderString("Content-Range"));
    Assertions.assertNotNull(response.getEntity());
  }

  @Test
  void testGetMoviesComingSoonByPaginationSecondPage() {
    // Arrange
    Mockito.when(movieService.getMoviesComingSoonByPagination(1, 1))
        .thenReturn(movies.subList(1, 2));
    Mockito.when(movieService.countMoviesComingSoon()).thenReturn((long) movies.size());

    // Act
    Response response = movieResource.getMoviesComingSoonByPagination(1, 1);

    // Then
    Assertions.assertEquals(Response.Status.PARTIAL_CONTENT.getStatusCode(), response.getStatus());
    Assertions.assertEquals("rows 1-2/2", response.getHeaderString("Content-Range"));
    Assertions.assertNotNull(response.getEntity());
  }
}
