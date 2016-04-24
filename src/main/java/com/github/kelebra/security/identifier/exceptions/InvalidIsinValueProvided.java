package com.github.kelebra.security.identifier.exceptions;

public class InvalidIsinValueProvided extends RuntimeException {

    public static final InvalidIsinValueProvided INSTANCE = new InvalidIsinValueProvided();

    public InvalidIsinValueProvided() {
        super("ISIN is 12 character string with first two letters as country code and rest - numbers");
    }
}
