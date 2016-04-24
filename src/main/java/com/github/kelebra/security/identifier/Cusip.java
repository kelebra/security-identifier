package com.github.kelebra.security.identifier;

import com.github.kelebra.security.identifier.check.ModuloTenCheckDigitProvider;
import com.github.kelebra.security.identifier.exceptions.CountryCodeNotValid;
import com.github.kelebra.security.identifier.generic.SecurityIdentifier;
import com.github.kelebra.security.identifier.check.LuhnCheckDigitProvider;
import com.github.kelebra.security.identifier.exceptions.InvalidCusipValueProvided;
import com.github.kelebra.security.identifier.util.InstanceProvider;

import static com.github.kelebra.security.identifier.util.Util.countryCodeIsValid;
import static com.github.kelebra.security.identifier.util.Util.padLeftWithZeroes;

public class Cusip extends SecurityIdentifier {

    private static final int LENGTH = 9;
    private static final ModuloTenCheckDigitProvider CHECK_DIGIT_PROVIDER =
            new ModuloTenCheckDigitProvider(new int[]{1, 2, 1, 2, 1, 2, 1, 2}, true);
    private static final InstanceProvider<Cusip> INSTANCE_PROVIDER = new InstanceProvider<Cusip>() {
        @Override
        public Cusip provide(String body, char checkDigit) {
            return new Cusip(body, checkDigit);
        }
    };

    public static Cusip from(String value) {
        return from(
                false,
                true,
                value.substring(0, value.length() - 1),
                value.charAt(value.length() - 1),
                LENGTH,
                CHECK_DIGIT_PROVIDER,
                InvalidCusipValueProvided.INSTANCE,
                INSTANCE_PROVIDER
        );
    }

    private Cusip(String body, char checkDigit) {
        super(body, checkDigit);
    }

    @Override
    public Isin isin(String countryCode) {
        if (!countryCodeIsValid(countryCode)) {
            throw new CountryCodeNotValid();
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
