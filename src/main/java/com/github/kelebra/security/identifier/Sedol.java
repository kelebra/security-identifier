package com.github.kelebra.security.identifier;

import com.github.kelebra.security.identifier.check.LuhnCheckDigitProvider;
import com.github.kelebra.security.identifier.generic.SecurityIdentifier;
import com.github.kelebra.security.identifier.util.Util;

public class Sedol extends SecurityIdentifier {

    private static final String UNITED_KINGDOM_COUNTRY_CODE = "GB";

    public static Sedol from(String value) {
        return new SedolBuilder().build(value);
    }

    Sedol(String body, char checkDigit) {
        super(body, checkDigit);
    }

    @Override
    public Isin isin(String countryCode) {
        if (!UNITED_KINGDOM_COUNTRY_CODE.equals(countryCode)) {
            throw new UnsupportedOperationException("Sedol cannot be converted to ISIN");
        } else {
            String isinBody = countryCode + Util.padLeftWithZeroes(body(), Isin.BODY_LENGTH);
            return Isin.from(isinBody + LuhnCheckDigitProvider.INSTANCE.checkDigit(isinBody));
        }
    }

    @Override
    public Sedol sedol() {
        return this;
    }

    @Override
    public Cusip cusip() {
        throw new UnsupportedOperationException("Sedol cannot be converted to CUSIP");
    }
}
