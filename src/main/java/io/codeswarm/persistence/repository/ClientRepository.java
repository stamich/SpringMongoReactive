package io.codeswarm.persistence.repository;

import io.codeswarm.persistence.model.Client;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {

    Flux<Client> findAll();

    @Query(value = "{ 'lastName' : ?0 }", fields = "{ 'firstName' : 1 , 'lastName' : 1 }")
    Flux<Client> findAllByLastName(String lastName);

    //@Query(value = "{ 'email' : ?0 }", fields = "{ 'firstName' : 1 , 'lastName' : 1, 'email' : 1 }")
    Mono<Client> findClientByEmail(String email);

    Mono<Void> deleteByEmail(String email);
}
