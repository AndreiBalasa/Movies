package ro.qual.movieRentals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.qual.movieRentals.converter.ClientConverter;
import ro.qual.movieRentals.dto.ClientDto;
import ro.qual.movieRentals.model.Client;
import ro.qual.movieRentals.service.ClientService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;


//    @GetMapping
//    List<Client> getAllClients(@RequestParam(defaultValue = "0") Integer pageNo,
//                               @RequestParam(defaultValue = "3") Integer pageSize,
//                               @RequestParam(defaultValue = "id") String sortBy) {
//        return clientService.getAllClients(pageNo,pageSize,sortBy);
//    }

    @GetMapping("/getwithRentals")
    List<Client> getAllClients(){
        return clientService.findAllWithRentals();
    }

    @PostMapping("/create")
    ClientDto addClient(@RequestBody ClientDto clientDto) {
        //todo:log parameters

        ClientDto resultModel = clientConverter.convertModelToDto(clientService.saveClient(
                clientConverter.convertDtoToModel(clientDto)));

        //todo:log result model

        return resultModel;
    }

    @PutMapping("/update/{id}")
    ClientDto updateClient(@PathVariable Long id,
                           @RequestBody ClientDto clientDto) {
        clientDto.setId(id);
        var resultModel = clientConverter.convertModelToDto(clientService.updateClient(
                clientConverter.convertDtoToModel(clientDto)));
        return resultModel;
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/filterClients/{s}")
    public Set<Client> filterClients(@PathVariable String s) {

        return clientService.filterClients(s);
    }

}
