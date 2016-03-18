package org.security.identifier;

import org.security.identifier.check.LuhnCheckDigitProvider;
import org.security.identifier.check.ModuloTenCheckDigitProvider;
import org.security.identifier.exceptions.CountryCodeNotValid;
import org.security.identifier.exceptions.InvalidCusipValueProvided;
import org.security.identifier.generic.SecurityIdentifier;
import org.security.identifier.util.InstanceProvider;

import static org.security.identifier.util.Util.countryCodeIsValid;
import static org.security.identifier.util.Util.padLeftWithZeroes;

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
