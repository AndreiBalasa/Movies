package ro.qual.movieRentals.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "movieWithRentals",
                attributeNodes = @NamedAttributeNode(value = "rentals"))
})

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
//@Table(name="movie")
public class Movie extends BaseEntity<Long> {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "genre", nullable = false)
    private String genre;

    public Movie(Long aLong) {
        super(aLong);

    }

    @JsonBackReference
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Rental> rentals = new HashSet<>();




    public Movie(String title, String description, String genre) {

        this.title = title;
        this.description = description;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                '}'+ super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) && Objects.equals(description, movie.description) && Objects.equals(genre, movie.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, description, genre);
    }
}