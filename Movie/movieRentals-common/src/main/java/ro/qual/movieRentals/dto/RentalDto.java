package ro.qual.movieRentals.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RentalDto extends  BaseDto {
    private Long movie_id;
    private Long client_id;
    private Date rented_date;
    private Date due_date;
    private Date returned_date;
}
