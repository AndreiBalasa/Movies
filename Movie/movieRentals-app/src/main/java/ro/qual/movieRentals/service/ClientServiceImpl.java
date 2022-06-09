package ro.qual.movieRentals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.qual.movieRentals.customExceptions.RentNotAllowedException;
import ro.qual.movieRentals.model.Client;
import ro.qual.movieRentals.model.Movie;
import ro.qual.movieRentals.model.Rental;
import ro.qual.movieRentals.repository.ClientRepository;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private MovieService movieService;

    @Override
    public List<Client> getAllClients(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Client> pagedResult = repository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Client>();
        }
    }

    @Override
    public List<Client> findAllWithRentals() {
         List<Client> all=repository.findAllWithRentals();
         return all;
    }

    @Override
    public Optional<Client> findClient(Long id) {
        Optional<Client> client = repository.findById(id);
        return client;
    }

    @Override
    public Client saveClient(Client client) {
        return (Client) repository.save(client);
    }

    @Override
    @Transactional
    public Client updateClient(Client client) {

        Client updateClient = repository.findById(client.getId()).orElseThrow();
        updateClient.setName(client.getName());
        updateClient.setRentals(client.getRentals());
        return updateClient;
    }

    @Override
    public void deleteClient(Long id) {

        repository.deleteById(id);
    }

    public void rentMovie(Long client_id, Long movie_id) {
        Client client = findClient(client_id).get();
        Movie movie = movieService.findMovie(movie_id).get();
        //todo: error should now be fixed - all sides of the relation should have been initialized; move logic out of controller (-->service); also, Client/Movie could expose an addRental() or rentMovie() method
        //todo: the rental 'dto' should contain only a client-id and a movie-id instead of the entire Client/Movie objects

        Set<Rental> rentals = movie.getRentals();
        Rental rental = new Rental();
        int dateExceededMovies = 0;


        //set Current date
        Calendar calendar = new GregorianCalendar();
        long currentTime = calendar.getTimeInMillis();
        java.sql.Date rented = new java.sql.Date(currentTime);
        for (Rental r : rentals) {
            if (r.getReturned_date() == null && r.getDue_date().before(rented))
                dateExceededMovies++;
        }

        rental.setRented_date(rented);

        //due date +7 days
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        long dueTime = calendar.getTimeInMillis();
        java.sql.Date due = new java.sql.Date(dueTime);
        rental.setDue_date(due);

        //Configure rental
        rental.setMovie(movie);
        rental.setClient(client);

        System.out.println(dateExceededMovies);
        //Save rental
        if (dateExceededMovies == 0) {
            rentals.add(rental);
            movie.setRentals(rentals);
            movieService.updateMovie(movie);
        } else {
            throw new RentNotAllowedException();
        }


    }

    public void returnMovie(Long client_id, Long movie_id) {
        Client client = findClient(client_id).get();
        Movie movie = movieService.findMovie(movie_id).get();
        Set<Rental> rentals = movie.getRentals();

        Calendar calendar = new GregorianCalendar();
        long currentTime = calendar.getTimeInMillis();
        java.sql.Date rented = new java.sql.Date(currentTime);

        for (Rental r : rentals) {
            if (r.getMovie().getId().equals(movie_id) && r.getClient().getId().equals(client_id)) {
                r.setReturned_date(rented);
                rentals.add(r);
                movie.setRentals(rentals);
                movieService.updateMovie(movie);

            }
        }
    }

    public Set<Client> filterClients(String s) {
        List<Client> list = repository.findAll();
        Set<Client> filteredList = list.stream()
                .filter(client -> client.toString().contains(s))
                .collect(Collectors.toSet());
        return filteredList;
    }
}
