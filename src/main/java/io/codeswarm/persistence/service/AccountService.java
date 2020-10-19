package io.codeswarm.persistence.service;

import io.codeswarm.persistence.model.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

    Flux<Account> findAll();
    Mono<Account> findAccountByOwnerEmail(String ownerEmail);
    Mono<Account> create(Account account);
    Mono<Account> update(Account account);
    Mono<Void> deleteAccountByOwnerEmail(String ownerEmail);
}
