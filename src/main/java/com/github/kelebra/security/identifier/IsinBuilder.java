package com.github.kelebra.security.identifier;

import com.github.kelebra.security.identifier.check.LuhnCheckDigitProvider;
import com.github.kelebra.security.identifier.exceptions.InvalidIsinValueProvided;
import com.github.kelebra.security.identifier.generic.SecurityIdentifierBuilder;

public class IsinBuilder extends SecurityIdentifierBuilder<Isin> {

    @Override
    protected int getRequiredLength() {
        return 12;
    }

    @Override
    protected Isin getSecurityIdentifier(String body, char checkDigit) {
        return new Isin(body, checkDigit);
    }

    @Override
    protected char getCheckDigit(String body) {
        return LuhnCheckDigitProvider.INSTANCE.checkDigit(body);
    }

    @Override
    protected boolean isAlphaNumeric() {
        return true;
    }

    @Override
    protected boolean hasCountryCode() {
        return true;
    }

    @Override
    protected RuntimeException getCreationException() {
        return InvalidIsinValueProvided.INSTANCE;
    }
}
