package com.github.kelebra.security.identifier.exceptions;

public class InvalidCusipValueProvided extends RuntimeException {

    public static final InvalidCusipValueProvided INSTANCE = new InvalidCusipValueProvided();

    public InvalidCusipValueProvided() {
        super("CUSIP value has to be nine characters alpha-numeric string");
    }
}
