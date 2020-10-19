package io.codeswarm.persistence.service;

import io.codeswarm.persistence.exception.AccountNotFoundException;
import io.codeswarm.persistence.model.Account;
import io.codeswarm.persistence.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Flux<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Mono<Account> findAccountByOwnerEmail(String ownerEmail) {
        return accountRepository.findAccountByOwnerEmail(ownerEmail);
    }

    @Override
    public Mono<Account> create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Mono<Account> update(Account account) {
//        return findAccountByOwnerEmail(account.getOwnerEmail())
//                .map(a -> new Account(a.getOwnerEmail(), a.getBalance(), a.getActive()))
//                .flatMap(accountRepository::save)
//                .switchIfEmpty(Mono.error(new AccountNotFoundException()));
        return this.accountRepository
                .findAccountByOwnerEmail(account.getOwnerEmail())
                .flatMap(a -> accountRepository.deleteAccountByOwnerEmail(account.getOwnerEmail()))
                .map(a -> new Account(account.getOwnerEmail(), account.getBalance(), account.getActive()))
                .flatMap(this.accountRepository::insert)
                .switchIfEmpty(Mono.error(new AccountNotFoundException()));
    }

    @Override
    public Mono<Void> deleteAccountByOwnerEmail(String ownerEmail) {
        return findAccountByOwnerEmail(ownerEmail)
                .switchIfEmpty(Mono.error(new AccountNotFoundException()))
                .flatMap(account ->
                        accountRepository.deleteAccountByOwnerEmail(account.getOwnerEmail()));
    }
}
