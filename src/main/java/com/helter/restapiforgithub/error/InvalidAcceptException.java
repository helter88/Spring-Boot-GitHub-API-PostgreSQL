package com.helter.restapiforgithub.error;

public class InvalidAcceptException extends RuntimeException {
    public InvalidAcceptException(String message) {
        super(message);
    }
}