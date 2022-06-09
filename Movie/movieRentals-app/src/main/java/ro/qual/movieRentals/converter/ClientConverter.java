package ro.qual.movieRentals.converter;

import org.springframework.stereotype.Component;
import ro.qual.movieRentals.dto.ClientDto;
import ro.qual.movieRentals.model.Client;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto>{
    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto dto = new ClientDto(client.getName());
        dto.setId(client.getId());
        return dto;
    }

    @Override
    public Client convertDtoToModel(ClientDto dto) {
        var client = new Client();
        client.setId(dto.getId());
        client.setName(dto.getName());
        return client;
    }
}
