package io.codeswarm.persistence.controller;


import io.codeswarm.persistence.model.Account;
import io.codeswarm.persistence.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/all")
    public Flux<Account> getAll() {
        return accountService.findAll();
    }

    @PostMapping("/byEmail")
    public Mono<Account> getByEmail(@RequestBody Account account) {
        return accountService.findAccountByOwnerEmail(account.getOwnerEmail());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Account> addAccount(@RequestBody Account account) {
        return accountService.create(account);
    }

    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Mono<Account> updateAccount(@RequestBody Account account) {
        return accountService.update(account);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<Void> deleteByEmail(@RequestBody Account account) {
        return accountService.deleteAccountByOwnerEmail(account.getOwnerEmail());
    }
}
