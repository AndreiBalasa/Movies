package ro.qual.movieRentals.service;

import ro.qual.movieRentals.model.Movie;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MovieService {

    List<Movie> getAllMovies(Integer pageNo, Integer pageSize, String sortBy);

    Movie saveMovie(Movie movie);

    Movie updateMovie(Movie movie);

    void deleteMovie(Long id);

    List<Movie> findAllWithRentals();

    Optional<Movie> findMovie(Long id);
    public Set<Movie> filterMovies(String s);
}
