package com.github.kelebra.security.identifier;

import com.github.kelebra.security.identifier.generic.SecurityIdentifier;

public class Isin extends SecurityIdentifier {

    protected static int BODY_LENGTH = 9;

    public static Isin from(String value) {
        return new IsinBuilder().build(value);
    }

    Isin(String body, char checkDigit) {
        super(body, checkDigit);
    }

    @Override
    public Isin isin(String countryCode) {
        return this;
    }

    @Override
    public Sedol sedol() {
        return Sedol.from(body());
    }

    @Override
    public Cusip cusip() {
        return Cusip.from(body());
    }

    public String countryCode() {
        return body().substring(0, 2);
    }
}
