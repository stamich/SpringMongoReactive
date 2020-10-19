package io.codeswarm.persistence.exception;

public class ClientNotFoundException extends Exception {
    public ClientNotFoundException() {
        super("Client not found!");
    }
}
