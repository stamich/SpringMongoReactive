package io.codeswarm.persistence.exception;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException() {
        super("Account not found!");
    }
}
