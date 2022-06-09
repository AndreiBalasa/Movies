package ro.qual.movieRentals.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "clientWithRentals",
                attributeNodes = @NamedAttributeNode(value = "rentals"))
})

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder

//@Table(name = "client")
public class Client extends BaseEntity<Long>{

   @Column(name = "name")
   private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Rental> rentals = new HashSet<>();

    public Client(String name) {
        this.name = name;
    }

    public Client(Long aLong) {
        super(aLong);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                '}';
    }


}
