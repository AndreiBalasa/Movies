package ro.qual.movieRentals.converter;

import ro.qual.movieRentals.dto.BaseDto;
import ro.qual.movieRentals.model.BaseEntity;

public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);
}
