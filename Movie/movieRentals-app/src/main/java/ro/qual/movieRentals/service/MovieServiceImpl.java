package ro.qual.movieRentals.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.qual.movieRentals.model.Client;
import ro.qual.movieRentals.model.Movie;
import ro.qual.movieRentals.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class MovieServiceImpl implements MovieService{


    @Autowired
    private MovieRepository repository;

    public List<Movie> getAllMovies(Integer pageNo, Integer pageSize, String sortBy){


        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Movie> pagedResult = repository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Movie>();
        }
    }

    @Override
    public Optional<Movie> findMovie(Long id) {
        Optional<Movie> movie=repository.findById(id);
        return movie;
    }

    @Override
    public Movie saveMovie(Movie movie) {

        return (Movie) repository.save(movie);
    }

    @Override
    @Transactional
    public Movie updateMovie(Movie movie) {

        Movie updateMovie = repository.findById(movie.getId()).orElseThrow();
        updateMovie.setTitle(movie.getTitle());
        updateMovie.setDescription(movie.getDescription());
        updateMovie.setGenre(movie.getGenre());
        return updateMovie;

    }

    @Override
    public void deleteMovie(Long id){
        repository.deleteById(id);
    }

    @Override
    public List<Movie> findAllWithRentals() {
        List<Movie> all=repository.findAllWithRentals();
        return all;
    }

    public Set<Movie> filterMovies(String s) {
            List<Movie> list=repository.findAll();
            Set<Movie> filteredList=list.stream()
                    .filter(movie -> movie.toString().contains(s))
                    .collect(Collectors.toSet());
            return filteredList;
        }


}
