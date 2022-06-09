package ro.qual.movieRentals.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.qual.movieRentals.model.Client;
import ro.qual.movieRentals.model.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends MovieRentalsRepository<Movie, Long> {
    @Query("select distinct m from Movie m where m.rentals is not empty")
    @EntityGraph(value = "movieWithRentals", type =
            EntityGraph.EntityGraphType.LOAD)
    List<Movie> findAllWithRentals();
}
