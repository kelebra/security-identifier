package org.security.identifier.exceptions;

public class CountryCodeNotValid extends RuntimeException {

    public static final CountryCodeNotValid INSTANCE = new CountryCodeNotValid();

    public CountryCodeNotValid() {
        super("Country code is not valid (2 letters, ISO 3166 standard)");
    }
}
