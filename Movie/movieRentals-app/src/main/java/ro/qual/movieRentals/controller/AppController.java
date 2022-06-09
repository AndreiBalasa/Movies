package ro.qual.movieRentals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.qual.movieRentals.model.Rental;
import ro.qual.movieRentals.service.ClientService;
import ro.qual.movieRentals.service.MovieService;

import java.util.Set;

@RestController
@RequestMapping("/app")
public class AppController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private ClientService clientService;


    @PutMapping("/rentMovie/{client_id}/{movie_id}")
    public void rentMovie(@PathVariable long client_id, @PathVariable long movie_id) {
        clientService.rentMovie(client_id, movie_id);
    }

    @PutMapping("/returnMovie/{client_id}/{movie_id}")
    public void returnMovie(@PathVariable long client_id, @PathVariable long movie_id) {
        clientService.returnMovie(client_id, movie_id);
    }

    @GetMapping("/getClientRentals/{id}")
    Set<Rental> getClientRentals(@PathVariable long id) {

        return clientService.findClient(id).get().getRentals();
    }

}
