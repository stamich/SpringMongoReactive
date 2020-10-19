package io.codeswarm.persistence.service;

import io.codeswarm.persistence.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    Flux<Client> findAll();
    Flux<Client> findAllByLastName(String lastName);
    Mono<Client> findClientByEmail(String email);
    Mono<Client> create(Client client);
    Mono<Client> update(Client client);
    Mono<Void> deleteByEmail(String email);
    Mono<Void> delete();
}
