package br.com.iwakoshi.cinemaapi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import br.com.iwakoshi.cinemaapi.model.entity.movie.Certificate;
import br.com.iwakoshi.cinemaapi.model.entity.movie.Country;
import br.com.iwakoshi.cinemaapi.model.entity.movie.Movie;

public class BuilderMovie {

  public static List<Movie> createMovies() {
    List<Movie> movies = new ArrayList<>();

    Country country = new Country();
    country.setCode("BR");
    country.setCurrencyIsoCode("BRL");
    country.setCurrencyName("Real");
    country.setCurrencySymbol("R$");
    country.setName("Brazil");

    Certificate certificate = new Certificate();
    certificate.setCountry(country);
    certificate.setDescription("12 Anos");
    certificate.setId(1);

    Movie movie = new Movie();
    movie.setAspectRatio("4:3");
    movie.setBudget(new BigDecimal(100));
    movie.setCountry(country);
    movie.setCreatedAt(Calendar.getInstance());
    movie.setDvdRelease(Calendar.getInstance());
    movie.setInsertUser("cinema@cinema-api.com.br");
    movie.setId(1L);
    movie.setMainCommentary("any thing");
    movie.setOriginalTitle("The Matrix");
    movie.setOverview("The Matrix Overview");
    movie.setPoster(null);
    movie.setProductionYear(1999);
    movie.setPublicRating(new BigDecimal(9.9));
    movie.setRating(new BigDecimal(9.8));
    movie.setReleaseDate(Calendar.getInstance());
    movie.setRuntime(120);
    movie.setUpdatedAt(Calendar.getInstance());
    movie.setUpdateUser("cinema@cinema-api.com.br");
    movies.add(movie);

    Movie movie2 = new Movie();
    movie2.setAspectRatio("4:3");
    movie2.setBudget(new BigDecimal(100));
    movie2.setCertificate(certificate);
    movie2.setCountry(country);
    movie2.setCreatedAt(Calendar.getInstance());
    movie2.setDvdRelease(Calendar.getInstance());
    movie2.setInsertUser("cinema@cinema-api.com.br");
    movie2.setId(1L);
    movie2.setMainCommentary("any thing");
    movie2.setOriginalTitle("Matrix Reload");
    movie2.setOverview("Matrix Reload Overview");
    movie2.setPoster(null);
    movie2.setProductionYear(1999);
    movie2.setPublicRating(new BigDecimal(9.9));
    movie2.setRating(new BigDecimal(9.8));
    movie2.setReleaseDate(Calendar.getInstance());
    movie2.setRuntime(120);
    movie2.setUpdatedAt(Calendar.getInstance());
    movie2.setUpdateUser("cinema@cinema-api.com.br");
    movies.add(movie2);

    country.setCertificates(new HashSet<>());
    country.addCertificate(certificate);
    country.removeCertificate(certificate);
    country.setMovies(new HashSet<>());
    country.addMovie(movie);
    country.removeMovie(movie);
    certificate.setMovies(new HashSet<>());
    certificate.addMovie(movie);
    certificate.removeMovie(movie);
    movie.setCertificate(certificate);
    certificate.setCountry(country);

    return movies;
  }
}
