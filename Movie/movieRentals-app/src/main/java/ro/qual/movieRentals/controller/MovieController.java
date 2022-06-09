package ro.qual.movieRentals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.qual.movieRentals.converter.MovieConverter;
import ro.qual.movieRentals.dto.MovieDto;
import ro.qual.movieRentals.model.Client;
import ro.qual.movieRentals.model.Movie;
import ro.qual.movieRentals.service.MovieService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieConverter movieConverter;

//    @GetMapping
//    List<Movie> getAllMovies(@RequestParam(defaultValue = "0") Integer pageNo,
//                             @RequestParam(defaultValue = "3") Integer pageSize,
//                             @RequestParam(defaultValue = "id") String sortBy) {
//        return movieService.getAllMovies(pageNo,pageSize,sortBy);
//    }

    @GetMapping("/getwithRentals")
    List<Movie> getAllMovies(){
        return movieService.findAllWithRentals();
    }

    @PostMapping("/create")
    MovieDto addMovie(@RequestBody MovieDto movieDto) {
        //todo:log parameters

        var resultModel = movieConverter.convertModelToDto(movieService.saveMovie(
                movieConverter.convertDtoToModel(movieDto)));

        //todo:log result model

        return resultModel;
    }

    @PutMapping("/update/{id}")
    MovieDto updateMovie(@PathVariable Long id,
                         @RequestBody MovieDto movieDto) {
        movieDto.setId(id);
        var resultModel = movieConverter.convertModelToDto(movieService.updateMovie(
                movieConverter.convertDtoToModel(movieDto)));
        return resultModel;
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/filterMovies/{s}")
    public Set<Movie> filterMovies(@PathVariable String s) {
        return movieService.filterMovies(s);
    }
}
