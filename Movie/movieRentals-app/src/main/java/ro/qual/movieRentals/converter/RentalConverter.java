//package ro.qual.movieRentals.converter;
//
//import org.springframework.stereotype.Component;
//import ro.qual.movieRentals.dto.RentalDto;
//import ro.qual.movieRentals.model.Rental;
//
//@Component
//public class RentalConverter extends BaseConverter<Rental, RentalDto> {
//    @Override
//    public RentalDto convertModelToDto(Rental rental) {
//        RentalDto dto=new RentalDto(rental.getMovie_id(), rental.getClient_id(), rental.getRented_date(),
//                rental.getDue_date(), rental.getReturned_date());
//        dto.setId(rental.getId());
//        return dto;
//    }
//
//    @Override
//    public Rental convertDtoToModel(RentalDto dto) {
//        Rental rental=new Rental();
//        rental.setId(dto.getId());
//        rental.setMovie_id(dto.getMovie_id());
//        rental.setClient_id(dto.getClient_id());
//        rental.setRented_date(dto.getRented_date());
//        rental.setDue_date(dto.getDue_date());
//        rental.setReturned_date(dto.getReturned_date());
//        return rental;
//    }
//}
