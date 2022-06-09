package ro.qual.movieRentals.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "rentals")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Rental implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rented_date", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Date rented_date;

    @Column(name = "due_date", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Date due_date;

    @Column(name = "returned_date")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Date returned_date;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;



    @Override
    public String toString() {
        return "Rental{" +
                ", rented_date=" + rented_date +
                ", due_date=" + due_date +
                ", returned_date=" + returned_date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Rental rental = (Rental) o;
        return id != null && Objects.equals(id, rental.id)
                && client != null && Objects.equals(client, rental.client)
                && movie != null && Objects.equals(movie, rental.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, movie);
    }
}

