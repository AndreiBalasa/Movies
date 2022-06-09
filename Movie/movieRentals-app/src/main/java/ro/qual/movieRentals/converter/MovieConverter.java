package ro.qual.movieRentals.converter;

import org.springframework.stereotype.Component;
import ro.qual.movieRentals.dto.MovieDto;
import ro.qual.movieRentals.model.Movie;

@Component
public class MovieConverter extends BaseConverter<Movie, MovieDto> {

    @Override
    public Movie convertDtoToModel(MovieDto dto) {
        var model = new Movie();
        model.setId(dto.getId());
        model.setTitle(dto.getTitle());
        model.setDescription(dto.getDescription());
        model.setGenre(dto.getGenre());
        return model;
    }

    @Override
    public MovieDto convertModelToDto(Movie movie) {
        MovieDto dto = new MovieDto(movie.getTitle(), movie.getDescription(), movie.getGenre());
        dto.setId(movie.getId());
        return dto;
    }
}