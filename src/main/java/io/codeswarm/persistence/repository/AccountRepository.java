package io.codeswarm.persistence.repository;

import io.codeswarm.persistence.model.Account;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {

    Flux<Account> findAll();

    //@Query(value = "{ 'ownerEmail' : ?0 }", fields = "{ ownerEmail : 1 }")
    Mono<Account> findAccountByOwnerEmail(String ownerEmail);

    @Query(value = "{ 'ownerEmail' : ?0 }", delete = true)
    Mono<Void> deleteAccountByOwnerEmail(String ownerEmail);
}
