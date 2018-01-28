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

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.Max;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.com.iwakoshi.cinemaapi.service.movie.MovieService;
import br.com.iwakoshi.cinemaapi.util.Util;

/**
 * @author Fabio Iwakoshi
 *
 */
@Path("movies")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {

  @Inject
  private MovieService movieService;

  /**
   * @param start
   * @param max
   * @return Coming Soon Movies
   */
  @GET
  @Path("coming-soon")
  public Response getMoviesComingSoonByPagination(
      @QueryParam("offset") @DefaultValue(value = "0") int offset,
      @QueryParam("limit") @Max(value = 100) @DefaultValue(value = "30") int limit) {
    return Util.paginacao(movieService.countMoviesComingSoon(),
        movieService.getMoviesComingSoonByPagination(offset, limit), offset, limit);
  }
}
