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

import java.util.Calendar;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.iwakoshi.cinemaapi.model.entity.movie.Movie;

/**
 * @author Fabio Iwakoshi
 *
 */
@ApplicationScoped
public class MovieRepository {

  @PersistenceContext
  private EntityManager entityManager;

  /**
   * @param start
   * @param max
   * @return Coming Soon Movies
   */
  public List<Movie> getMoviesComingSoonByPagination(int start, int max) {
    return entityManager.createNamedQuery("Movie.getMoviesComingSoonByPagination", Movie.class)
        .setParameter("today", Calendar.getInstance())
        .setFirstResult(start).setMaxResults(max).getResultList();
  }

  /**
   * @return Total Coming Soon Movies
   */
  public Long countMoviesComingSoon() {
    return entityManager.createNamedQuery("Movie.countMoviesComingSoon", Long.class)
        .setParameter("today", Calendar.getInstance())
        .getSingleResult();
  }
}
