package com.github.kelebra.security.identifier.generic;

import com.github.kelebra.security.identifier.Cusip;
import com.github.kelebra.security.identifier.Isin;
import com.github.kelebra.security.identifier.Sedol;
import com.github.kelebra.security.identifier.check.CheckDigitProvider;
import com.github.kelebra.security.identifier.exceptions.CountryCodeNotValid;
import com.github.kelebra.security.identifier.exceptions.InvalidCheckDigitInSecurityIdentifier;
import com.github.kelebra.security.identifier.util.InstanceProvider;

import java.io.Serializable;

import static com.github.kelebra.security.identifier.util.Util.*;

public abstract class SecurityIdentifier implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String body;
    private final char checkDigit;

    protected static <T extends SecurityIdentifier> T from(boolean hasCountryCode,
                                                           boolean alphaNumeric,
                                                           String body,
                                                           char checkDigit,
                                                           int requiredLength,
                                                           CheckDigitProvider checkDigitProvider,
                                                           RuntimeException creationException,
                                                           InstanceProvider<T> instanceProvider) {
        if (alphaNumeric && !isAlphaNumeric(body)) throw creationException;

        String countryCode = hasCountryCode ? body.substring(0, 2).toUpperCase() : null;

        if (countryCode != null && !countryCodeIsValid(countryCode)) {
            throw new CountryCodeNotValid();
        }

        body = body.toUpperCase();
        int fullLength = body.length() + 1;

        if (fullLength > requiredLength || (hasCountryCode && fullLength != requiredLength)) {
            throw creationException;
        }

        if (!hasCountryCode) {
            body = padLeftWithZeroes(body, requiredLength - 1);
        }

        char c = checkDigitProvider.checkDigit(body);
        if (c != checkDigit) {
            throw new InvalidCheckDigitInSecurityIdentifier();
        }

        return instanceProvider.provide(body, checkDigit);
    }

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