package ro.qual.movieRentals.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.qual.movieRentals.dto.ClientDto;
import ro.qual.movieRentals.dto.ClientsDto;

@Component
public class ClientConsole {
    @Autowired
    private RestTemplate restTemplate;

    public void runConsole() {
        System.out.println("ui console .............");

        ClientsDto clientsDto = restTemplate.getForObject("http://localhost:8080/api/clients", ClientsDto.class);
        System.out.println(clientsDto);

        ClientDto client=new ClientDto();
        client.setName("Io");

        ClientDto savedClient = restTemplate.postForObject("http://localhost:8080/api/clients/create",
                client, ClientDto.class);
        System.out.println("saved client:");
        System.out.println(savedClient.toString());

        savedClient.setName("update-cl");
        restTemplate.put("http://localhost:8080/api/clients/update" + "/{id}", savedClient, savedClient.getId());
        System.out.println("update:");
        System.out.println(restTemplate.getForObject("http://localhost:8080/api/clients",
                ClientDto.class, savedClient.getId()).toString()); //value=null!!!!

        restTemplate.delete("http://localhost:8080/api/clients/delete"+ "/{id}", savedClient.getId());
        System.out.println("delete:");
        System.out.println(restTemplate.getForObject("http://localhost:8080/api/clients", ClientDto.class));

    }
}
