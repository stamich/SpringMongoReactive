package io.codeswarm.persistence.service;

import io.codeswarm.persistence.exception.ClientNotFoundException;
import io.codeswarm.persistence.model.Client;
import io.codeswarm.persistence.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Flux<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Flux<Client> findAllByLastName(String lastName) {
        return clientRepository.findAllByLastName(lastName);
    }

    @Override
    public Mono<Client> findClientByEmail(String email) {
        return clientRepository.findClientByEmail(email);
    }

    @Override
    public Mono<Client> create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Mono<Client> update(Client client) {
        return findClientByEmail(client.getEmail())
                .flatMap(clientRepository::save)
                .switchIfEmpty(Mono.error(new ClientNotFoundException()));
    }

    @Override
    public Mono<Void> deleteByEmail(String email) {
        return findClientByEmail(email)
                .switchIfEmpty(Mono.error(new ClientNotFoundException()))
                .flatMap(client ->
                        clientRepository.deleteByEmail(client.getEmail()));
    }

    @Override
    public Mono<Void> delete() {
        return clientRepository.deleteAll();
    }
}
