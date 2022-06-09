package ro.qual.movieRentals.service;

import ro.qual.movieRentals.model.Client;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ClientService {

    List<Client> getAllClients(Integer pageNo, Integer pageSize, String sortBy);

    List<Client> findAllWithRentals();

    Optional<Client> findClient(Long id);

    Client saveClient(Client client);

    Client updateClient(Client client);

    void deleteClient(Long id);

    public void rentMovie(Long client_id,Long movie_id);

    public void returnMovie(Long client_id,Long movie_id);

    public Set<Client> filterClients(String s);
}
