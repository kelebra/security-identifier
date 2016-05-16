package com.github.kelebra.security.identifier;

import com.github.kelebra.security.identifier.check.CheckDigitProvider;
import com.github.kelebra.security.identifier.check.ModuloTenCheckDigitProvider;
import com.github.kelebra.security.identifier.exceptions.InvalidSedolValueProvided;
import com.github.kelebra.security.identifier.generic.SecurityIdentifierBuilder;

public class SedolBuilder extends SecurityIdentifierBuilder<Sedol> {

    private static final CheckDigitProvider CHECK_DIGIT_PROVIDER =
            new ModuloTenCheckDigitProvider(new int[]{1, 3, 1, 7, 3, 9, 1}, false);

    @Override
    protected int getRequiredLength() {
        return 7;
    }

    @Override
    protected Sedol getSecurityIdentifier(String body, char checkDigit) {
        return new Sedol(body, checkDigit);
    }

    @Override
    protected char getCheckDigit(String body) {
        return CHECK_DIGIT_PROVIDER.checkDigit(body);
    }

    @Override
    protected boolean isAlphaNumeric() {
        return true;
    }

    @Override
    protected boolean hasCountryCode() {
        return false;
    }

    @Override
    protected RuntimeException getCreationException() {
        return InvalidSedolValueProvided.INSTANCE;
    }
}
