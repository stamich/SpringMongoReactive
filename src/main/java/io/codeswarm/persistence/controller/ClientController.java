package io.codeswarm.persistence.controller;

import io.codeswarm.persistence.model.Client;
import io.codeswarm.persistence.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public Flux<Client> getAllClients() {
        return clientService.findAll();
    }

    @PostMapping("/byName")
    public Flux<Client> getAllByLastName(@RequestBody Client client) {
        return clientService.findAllByLastName(client.getLastName());
    }

    @PostMapping("/byEmail")
    public Mono<Client> getClientByEmail(@RequestBody Client client) {
        return clientService.findClientByEmail(client.getEmail());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Client> addClient(@RequestBody Client client) {
        return clientService.create(client);
    }

    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Mono<Client> updateClient(@RequestBody Client client) {
        return clientService.update(client);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<Void> deleteClientByEmail(String email) {
        return clientService.deleteByEmail(email);
    }

    @DeleteMapping("/deleteAll")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAll() {
        return clientService.delete();
    }
}
