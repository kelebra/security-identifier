package com.github.kelebra.security.identifier.generic;

import com.github.kelebra.security.identifier.Cusip;
import com.github.kelebra.security.identifier.Isin;
import com.github.kelebra.security.identifier.Sedol;

import java.io.Serializable;

public abstract class SecurityIdentifier implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String body;
    private final char checkDigit;

    protected SecurityIdentifier(String body, char checkDigit) {
        this.body = body;
        this.checkDigit = checkDigit;
    }

    public abstract Isin isin(String countryCode);

    public abstract Sedol sedol();

    public abstract Cusip cusip();

    public String body() {
        return body;
    }

    public char checkDigit() {
        return checkDigit;
    }

    public boolean isValid() {
        String value = toString();
        return value.length() != 0 && value.charAt(value.length() - 1) == checkDigit();
    }

    @Override
    public String toString() {
        return body + checkDigit;
    }
}
