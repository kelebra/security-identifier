package org.security.identifier.exceptions;

public class InvalidCheckDigitInSecurityIdentifier extends RuntimeException {

    public static final InvalidCheckDigitInSecurityIdentifier INSTANCE = new InvalidCheckDigitInSecurityIdentifier();

    public InvalidCheckDigitInSecurityIdentifier() {
        super("Check digit did not match");
    }
}
