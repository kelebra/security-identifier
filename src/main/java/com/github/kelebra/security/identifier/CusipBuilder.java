package com.github.kelebra.security.identifier;

import com.github.kelebra.security.identifier.check.ModuloTenCheckDigitProvider;
import com.github.kelebra.security.identifier.exceptions.InvalidCusipValueProvided;
import com.github.kelebra.security.identifier.generic.SecurityIdentifierBuilder;

public class CusipBuilder extends SecurityIdentifierBuilder<Cusip> {

    private static final ModuloTenCheckDigitProvider CHECK_DIGIT_PROVIDER =
            new ModuloTenCheckDigitProvider(new int[]{1, 2, 1, 2, 1, 2, 1, 2}, true);

    @Override
    protected int getRequiredLength() {
        return 9;
    }

    @Override
    protected Cusip getSecurityIdentifier(String body, char checkDigit) {
        return new Cusip(body, checkDigit);
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
        return InvalidCusipValueProvided.INSTANCE;
    }
}
