package org.security.identifier.exceptions;

public class InvalidSedolValueProvided extends RuntimeException {

    public static final InvalidSedolValueProvided INSTANCE = new InvalidSedolValueProvided();

    public InvalidSedolValueProvided() {
        super("Invalid SEDOL value provided. SEDOL is seven alpha-numeric characters string");
    }
}
