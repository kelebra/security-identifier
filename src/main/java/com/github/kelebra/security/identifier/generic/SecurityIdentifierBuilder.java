package com.github.kelebra.security.identifier.generic;

import com.github.kelebra.security.identifier.exceptions.CountryCodeNotValid;
import com.github.kelebra.security.identifier.exceptions.InvalidCheckDigitInSecurityIdentifier;
import com.github.kelebra.security.identifier.util.Util;

import static com.github.kelebra.security.identifier.util.Util.countryCodeIsValid;
import static com.github.kelebra.security.identifier.util.Util.padLeftWithZeroes;

public abstract class SecurityIdentifierBuilder<T extends SecurityIdentifier> {

    private boolean shouldCheckCheckDigit = true;

    protected abstract int getRequiredLength();

    protected abstract T getSecurityIdentifier(String body, char checkDigit);

    protected abstract char getCheckDigit(String body);

    protected abstract boolean isAlphaNumeric();

    protected abstract boolean hasCountryCode();

    protected abstract RuntimeException getCreationException();

    public SecurityIdentifierBuilder<T> withoutCheckOfCheckDigit() {
        shouldCheckCheckDigit = false;
        return this;
    }

    public T build(String value) {
        char checkDigit = value.charAt(value.length() - 1);
        String body = value.substring(0, value.length() - 1);

        if (isAlphaNumeric() && !Util.isAlphaNumeric(body)) throw getCreationException();

        String countryCode = hasCountryCode() ? body.substring(0, 2).toUpperCase() : null;

        if (countryCode != null && !countryCodeIsValid(countryCode)) {
            throw CountryCodeNotValid.INSTANCE;
        }

        body = body.toUpperCase();
        int fullLength = body.length() + 1;

        if (fullLength > getRequiredLength() || (hasCountryCode() && fullLength != getRequiredLength())) {
            throw getCreationException();
        }

        if (!hasCountryCode()) body = padLeftWithZeroes(body, getRequiredLength() - 1);

        if (shouldCheckCheckDigit && getCheckDigit(body) != checkDigit) {
            throw InvalidCheckDigitInSecurityIdentifier.INSTANCE;
        }

        return getSecurityIdentifier(body, checkDigit);
    }
}
