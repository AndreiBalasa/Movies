package ro.qual.movieRentals.repository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.qual.movieRentals.model.Client;

import java.util.List;


@Repository
public interface ClientRepository extends MovieRentalsRepository<Client, Long>{
    @Query("select distinct c from Client c where c.rentals is not empty")
    @EntityGraph(value = "clientWithRentals", type =
            EntityGraph.EntityGraphType.LOAD)
    List<Client> findAllWithRentals();


}
