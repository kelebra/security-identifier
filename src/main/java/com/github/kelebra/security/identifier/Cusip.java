package com.github.kelebra.security.identifier;

import com.github.kelebra.security.identifier.check.LuhnCheckDigitProvider;
import com.github.kelebra.security.identifier.exceptions.CountryCodeNotValid;
import com.github.kelebra.security.identifier.exceptions.InvalidCheckDigitInSecurityIdentifier;
import com.github.kelebra.security.identifier.exceptions.InvalidCusipValueProvided;
import com.github.kelebra.security.identifier.generic.SecurityIdentifier;

import static com.github.kelebra.security.identifier.util.Util.countryCodeIsValid;
import static com.github.kelebra.security.identifier.util.Util.padLeftWithZeroes;

public class Cusip extends SecurityIdentifier {

    public static Cusip from(String value) throws InvalidCusipValueProvided, InvalidCheckDigitInSecurityIdentifier, CountryCodeNotValid {
        return new CusipBuilder().build(value);
    }

    Cusip(String body, char checkDigit) {
        super(body, checkDigit);
    }

    @Override
    public Isin isin(String countryCode) throws CountryCodeNotValid {
        if (!countryCodeIsValid(countryCode)) {
            throw CountryCodeNotValid.INSTANCE;
        } else {
            String isinBody = countryCode + padLeftWithZeroes(toString(), Isin.BODY_LENGTH);
            return Isin.from(isinBody + LuhnCheckDigitProvider.INSTANCE.checkDigit(isinBody));
        }
    }

    @Override
    public Sedol sedol() {
        throw new UnsupportedOperationException("CUSIP cannot be converted to SEDOL");
    }

    @Override
    public Cusip cusip() {
        return this;
    }
}
