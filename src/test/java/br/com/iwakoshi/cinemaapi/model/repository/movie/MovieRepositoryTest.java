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
package br.com.iwakoshi.cinemaapi.model.repository.movie;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import br.com.iwakoshi.cinemaapi.BuilderMovie;
import br.com.iwakoshi.cinemaapi.MockitoExtension;
import br.com.iwakoshi.cinemaapi.model.entity.movie.Movie;

/**
 * Classe de testes de ParameterRepository
 * 
 * @author Fabio Iwakoshi
 *
 */
@ExtendWith(MockitoExtension.class)
public class MovieRepositoryTest {

  @Mock
  private EntityManager em;

  @Mock
  private TypedQuery<Movie> typedQuery;

  @Mock
  private TypedQuery<Long> typedQueryInteger;

  @InjectMocks
  private MovieRepository movieRepository;

  @Test
  public void testGetMoviesComingSoonByPagination() {
    // Arrange
    Mockito.when(em.createNamedQuery(Mockito.anyString(), Mockito.<Class<Movie>>any()))
        .thenReturn(typedQuery);
    Mockito.when(typedQuery.setMaxResults(Mockito.anyInt())).thenReturn(typedQuery);
    Mockito.when(typedQuery.setFirstResult(Mockito.anyInt())).thenReturn(typedQuery);
    Mockito.when(typedQuery.setParameter(Mockito.anyString(), Mockito.any()))
        .thenReturn(typedQuery);
    Mockito.when(typedQuery.getResultList()).thenAnswer(new Answer<List<Movie>>() {
      @Override
      public List<Movie> answer(InvocationOnMock invocation) throws Throwable {
        return BuilderMovie.createMovies();
      }
    });

    // Act
    List<Movie> listMovies = movieRepository.getMoviesComingSoonByPagination(0, 30);

    // Then
    Assertions.assertEquals(2, listMovies.size());
  }

  @Test
  public void testCountMoviesComingSoon() {
    // Arrange
    Mockito.when(em.createNamedQuery(Mockito.anyString(), Mockito.<Class<Long>>any()))
        .thenReturn(typedQueryInteger);
    Mockito.when(typedQueryInteger.getSingleResult())
        .thenReturn((long) BuilderMovie.createMovies().size());
    Mockito.when(typedQueryInteger.setParameter(Mockito.anyString(), Mockito.any()))
        .thenReturn(typedQueryInteger);

    // Act
    Long count = movieRepository.countMoviesComingSoon();

    // Then
    Assertions.assertEquals(2, count.longValue());
  }

}
