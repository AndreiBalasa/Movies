package ro.qual.movieRentals.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MovieDto extends BaseDto{
    private String title;
    private String description;
    private String genre;

}
